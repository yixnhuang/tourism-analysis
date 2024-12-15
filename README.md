# Tourism Website Big Data Analysis and Application

## Project Overview

This project focuses on big data analysis and application for tourism websites, specifically for scraping, cleaning, storing, analyzing, and visualizing hotel data from travel sites (such as Ctrip). The goal is to extract meaningful insights from a vast amount of unstructured data, allowing users to make data-driven decisions when planning their travels. The system implements data collection using web scraping, cleaning, storage with HBase, data analysis with MapReduce, and data visualization with ECharts.

## Features

This project includes the following main features:

1. **Data Scraping**: 
   - Uses Jsoup to scrape hotel data from tourism websites (such as Ctrip), including city information, hotel names, prices, ratings, and more.
  
2. **Data Cleaning**: 
   - Cleans the scraped HTML data using Jsoup, removing irrelevant elements and structuring the data for further analysis.

3. **Data Storage**: 
   - Utilizes HBase to store structured hotel data in a distributed, column-based database, taking advantage of HBase's scalability and performance.

4. **Data Analysis**: 
   - Implements data analysis using Hadoop's MapReduce, including tasks like calculating the average hotel price by city and performing word frequency analysis on hotel reviews.

5. **Data Visualization**: 
   - Uses ECharts to create interactive visualizations such as hotel price distribution, average price comparison, and room type statistics.

## Technologies Used

- **Jsoup**: A Java library for HTML parsing and web scraping.
- **HBase**: A distributed, column-oriented NoSQL database for storing structured data.
- **Hadoop**: A framework for distributed storage and processing of large data sets, used for MapReduce jobs.
- **ECharts**: A JavaScript library for creating interactive and customizable data visualizations.
- **Java**: The primary programming language used to implement the solution.

## Project Structure

.
├── data_scraping
│   ├── HotelDataScraper.java    # Main scraper code
│   └── utils
│       └── StringUtil.java      # Utility classes for data extraction
├── data_cleaning
│   ├── DataCleaner.java        # Logic for cleaning scraped data
│   └── models
│       └── HotelCity.java      # Data model for hotel cities
├── data_storage
│   ├── HBaseUtil.java          # Utility class for interacting with HBase
│   └── config
│       └── HBaseConfig.java    # Configuration for HBase setup
├── data_analysis
│   ├── MapReduceJobs.java      # Hadoop MapReduce jobs for data analysis
│   └── analysis
│       └── HotelPriceAnalysis.java  # Logic for analyzing hotel prices
├── data_visualization
│   ├── charts
│   │   ├── price_distribution.js    # ECharts visualization for price distribution
│   │   └── room_type_stats.js       # ECharts visualization for room type stats
│   └── index.html              # Main HTML file to render the visualizations
└── README.md                   # Project documentation (this file)


## Installation and Usage

### Prerequisites

- Java 8 or higher
- HBase 2.0 or higher
- Hadoop 3.x
- Maven (for dependency management)
- ECharts (for visualization)

## Contributing

Contributions to this project are welcome! If you have suggestions, bug fixes, or improvements, feel free to fork this repository and submit a pull request.

### Steps to Contribute:
1. Fork this repository.
2. Create a new branch (`git checkout -b feature-xyz`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-xyz`).
5. Create a new pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Special thanks to the creators of [ECharts](https://echarts.apache.org/) and [Jsoup](https://jsoup.org/).
- Hadoop and HBase communities for providing the necessary big data tools.
- The tourism website data source for providing rich datasets for analysis.

### Key Sections:

1. **Project Overview**: Briefly explains the objective of the project.
2. **Features**: Describes the key functionalities that the project implements.
3. **Technologies Used**: Lists the technologies used in the project.
4. **Project Structure**: Provides a hierarchical view of the project folder structure.
5. **Installation and Usage**: Explains the setup process and how to run the different parts of the system.
6. **Example Output**: Describes the expected output of the system, such as the visualizations.
7. **Contributing**: Provides guidelines for contributing to the project.
8. **License**: Mentions the project's open-source license.
9. **Acknowledgments**: Credits to the libraries or tools used in the project.

This should provide a solid foundation for your GitHub README. Feel free to modify or expand on this template to better suit your project specifics!