import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.apache.spark._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._



object Olist_Ecommerce {

  /** Our main function where the action happens */
  def main(args: Array[String]) {

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine,
    val spark = SparkSession.builder.master("local[*]").appName("OlistEcomerce").getOrCreate()

    // Read data file
    val data = spark.read.options(Map("inferSchema"->"true","delimiter"->",","header"->"true"))
      .csv("data/full_data_1.csv")
//        data.printSchema()
//        data.show(10)

    // Drop some of the redundant columns
    val droppedGeoData = data.drop("geolocation_lat","geolocation_lng","geolocation_city","geolocation_state"
      ,"customer_zip_code_prefix")

    // Remove the duplication
    val no_duplication_data = droppedGeoData.distinct()
    println("Distinct count: "+ no_duplication_data.count().toString)

    // Question 1: What are the number of order per customer?
    val numberOrderPerCustomer = no_duplication_data.groupBy("customer_id")
      .agg(count("order_id")
      .alias("cnt"))
      .orderBy(col("cnt").asc).show(5)

    // Question 2: What are the best selling product categories?

    // Check the number of product category
    val  numberOfProductCategories = no_duplication_data.select("product_category_name_english").distinct().count()
    print("Number of product category is: " + numberOfProductCategories.toString)

    // Check the best-selling product categories
    val numberOfSalesByCategory = no_duplication_data.groupBy("product_category_name_english").count()
      .orderBy(col("count").desc).show(5)

    // Question 3: Which period has the most sell?

    // Correct the timestamp column from String to Datetime
    val dateTimePattern = "dd/MM/yyyy hh:mm:ss aa" //current datetime pattern
    spark.conf.set("spark.sql.legacy.timeParserPolicy","LEGACY") //define the timeParserPolicy to deal with dates

    val converted_df = no_duplication_data.withColumn("converted_order_purchase_timestamp",
      to_timestamp(col("order_purchase_timestamp"), "dd/MM/yyyy hh:mm:ss aa"))
      .drop(col("order_purchase_timestamp"))

    // Check the record period
   converted_df.select(min("converted_order_purchase_timestamp")
      ,max("converted_order_purchase_timestamp")).show()

    // Filter the data from 2017 to 2018
    val above2017Data = converted_df.filter(converted_df("converted_order_purchase_timestamp")
      > lit("2017-01-01 00:00:00")).orderBy(col("converted_order_purchase_timestamp"))

    // Count and sort the transaction by month and year
    val monthlyTransactionCount = above2017Data.groupBy(
       month(col("converted_order_purchase_timestamp")).alias("month")
      ,year(col("converted_order_purchase_timestamp")).alias("year"))
    .agg(count("converted_order_purchase_timestamp").alias("count_transaction"))
    .orderBy(col("month").asc,col("year").asc)

    monthlyTransactionCount.sort(col("count_transaction")).show()

    // Stop the session
    spark.stop()
  }
}
