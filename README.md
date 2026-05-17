# Tourism Website Big Data Analysis and Application

## Project Overview

This project focuses on big data analysis and application for tourism websites, specifically on scraping, cleaning, storing, analyzing, and visualizing hotel data from travel platforms (such as Ctrip).

The goal is to extract meaningful insights from large-scale unstructured data and support data-driven decision-making for travel planning.

This project was developed as part of the Big Data Technology course at university.

## System Pipeline

1. Data Scraping  
   Uses Jsoup to extract hotel information from tourism websites, including city, hotel name, price, rating, and other attributes.

2. Data Cleaning  
   Processes raw HTML data, removes noise, and structures it for analysis.

3. Data Storage  
   Uses HBase for distributed column-based storage.

4. Data Analysis  
   Uses Hadoop MapReduce to perform batch analysis, including:
   - Average hotel price per city
   - Word frequency analysis on reviews

5. Data Visualization  
   Uses ECharts to build interactive visualizations such as price distribution and city comparisons.

## Technologies Used

Java  
Jsoup  
Hadoop MapReduce  
HBase  
ECharts

## Purpose

This repository is a coursework project for learning and demonstration purposes.

It is intended for academic use and personal portfolio only.

## License

This project is distributed under the:

ACADEMIC ATTRIBUTION AND RESTRICTED USE LICENSE  
Version 1.0.0

See the `LICENSE` file for full license terms.

## Attribution

If you use, modify, reference, or redistribute this project or any substantial portion of it, you must provide clear attribution:

> Based on work by Yixuan Huang

## Author

Yixuan Huang  
Email: `yixnhuang@gmail.com"