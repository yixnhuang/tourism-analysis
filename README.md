# Tourism Website Big Data Analysis and Application

## Project Overview

This project focuses on big data analysis and application for tourism websites,
specifically on scraping, cleaning, storing, analyzing, and visualizing hotel
data from travel platforms such as Ctrip.

The goal is to extract meaningful insights from large-scale unstructured data
and support data-driven decision-making for travel planning.

This project was developed as part of the Big Data Technology course at
university.

## System Pipeline

1. Data Scraping  
   Uses Jsoup to extract hotel information from tourism websites, including
   city, hotel name, price, rating, and other attributes.

2. Data Cleaning  
   Processes raw HTML data, removes noise, and structures it for analysis.

3. Data Storage  
   Uses HBase for distributed column-based storage.

4. Data Analysis  
   Uses Hadoop MapReduce to perform batch analysis, including:
   - Average hotel price per city
   - Word frequency analysis on reviews

5. Data Visualization  
   Uses ECharts to build interactive visualizations such as price distribution
   and city comparisons.

## Technologies Used

- Java
- Jsoup
- Hadoop MapReduce
- HBase
- ECharts

## Purpose

This repository was developed as a coursework project for learning,
demonstration, and portfolio purposes.

The source code is shared to document the project and may be reused under
the terms of the MIT License.

## Data and Third-Party Content Notice

This repository may reference or process data from third-party travel
platforms. Any third-party data, website content, trademarks, course materials,
and external resources remain the property of their respective owners and are
not covered by this repository's license.

Users are responsible for complying with applicable terms of service, data
usage policies, and laws when collecting or using external data.

## License

Copyright 2025 Yixuan Huang

This project is licensed under the MIT License.
See the [LICENSE](LICENSE) file for details.

Unless otherwise stated, this license applies only to the original code
written by the author. Course materials, datasets, scraped data, third-party
libraries, website content, and external resources remain under their
respective rights and licenses.

## Attribution

The MIT License requires preservation of the copyright and license notices.

Additional visible attribution is appreciated:

> Based on work by Yixuan Huang

## Author

Yixuan Huang  
Email: `yixnhuang@gmail.com`