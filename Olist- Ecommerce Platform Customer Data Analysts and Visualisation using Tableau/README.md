# Olist- Ecommerce Platform Customer Data Analysts and Visualisation using Tableau
> Using Tableau for visualization, besides data analysis, machine learning techniques to answer the business question "Who is making money on our e-commerce platform?" which is valuable to the manager by making better business decisions or marketers by optimizing the marketing strategy.

## Questions: (Problem Framing)
* Who is making money on our e-commerce platform?  
    Who are those sellers? What do they sell? Where are they?  
    Where are their customers? (where are 20% of the customer that
bring them 80% revenue)  
    What are the marketing channels the company used to acquire them?
    Who are the Sale Development Reps that won those sellers?

* Why those questions​ : From those the Infos and analysis we could:
    1. Upsales those sellers by providing them more services (sell strategy consultancy,
advertisements..).
    2. Find more similar sellers (based on location, industry - could be similar or relatively
alike, or marketing channels).
    3. Training other Sale Reps based on the methodology of the Sale Development
Reps that won those sellers or allocate the similar leads to those Sale Reps.

## Data Sources:
1. Brazilian E-Commerce Public Dataset by Olist: information of 100k orders from
2016 to 2018 made at multiple marketplaces in Brazil
2. Marketing Funnel by Olist - ​ marketing funnel dataset from sellers that filled-in requests
of contact to sell their products on ​ Olist Store​ . The dataset has information of 8k
Marketing Qualified Leads (MQLs) that requested contact between Jun. 1st 2017 and
Jun 1st, 2018. They were randomly sampled from a total of MQLs.  

(Note: those are ​ real data, sampled from the original dataset but has been anonymized)

## Data Description
1. E-Commerce Public Dataset - Tabular, spatial, text, DateTime data in CSV format
including 9 files for the different datasets: customer, seller, payment, order, products...
But we will mainly focus on:  
  a. Seller data: 3095 rows x 4 columns  
  b. Customer data: 99441 rows x 5 columns  
  c. Product data:32k rows x 9 columns  
Link:​ https://www.kaggle.com/olistbr/brazilian-ecommerce#product_category_nam
e_translation.csv

2. ​ Marketing funnel​ - Tabular, spatial, categorical, DateTime data in CSV format - contains
2 files:  
a. Closed_deal: 842 rows x 14 columns. Including categorical, numerical, DateTime
data. But we mainly focus on Marketing Qualified Lead Id for merging data, and
business_segment, business type  
b. Marketing_qualified_leads: 8000 rows x 4 columns. Focus on mql_id(merging)
and origin (tracking the source of marketing channel)
Link:​ https://www.kaggle.com/olistbr/marketing-funnel-olist/home#olist_marketing_
qualified_leads_dataset.csv

