# Tourism Data Analysis

A Java data-processing project for collecting, cleaning, storing, and analyzing
hotel information from travel platforms. It demonstrates an end-to-end pipeline
with Jsoup, HBase, Hadoop MapReduce, and browser-based visualization.

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Status](https://img.shields.io/badge/Status-Complete-success.svg)](#project-status)
[![Java](https://img.shields.io/badge/Java-8-informational.svg)](#requirements)

## Overview

This repository explores a tourism-data workflow from HTML collection and
cleaning through distributed storage and batch analysis. Hotel prices, names,
ratings, and review text are transformed into structured records that can be
queried in HBase and summarized with MapReduce jobs.

The project is retained as a completed data-engineering study. Its dependencies
target an older Java and Hadoop ecosystem, so reproducing the full pipeline
requires a compatible local or distributed environment.

## Pipeline

1. **Collection** — Uses Jsoup to parse hotel and destination pages.
2. **Cleaning** — Converts raw HTML and JSON responses into structured records.
3. **Storage** — Stores hotel and review data in HBase.
4. **Analysis** — Calculates statistics such as average city hotel prices and
   review word frequencies with Hadoop MapReduce.
5. **Visualization** — Prepares results for ECharts and word-cloud views.

## Requirements

- JDK 8
- Maven
- Hadoop 2.7.2
- HBase 1.3.1
- A configured ZooKeeper/HBase environment

Install Java dependencies with:

```bash
mvn dependency:resolve
```

The existing Maven configuration contains a machine-specific JDK tools path.
Adjust `pom.xml` for the local JDK installation before building.

## Project Structure

```text
src/main/java/
├── A_DataCapture/    # Source collection and parsing
├── B_DataClean/      # HTML and record cleaning
├── C_DataToHbase/    # HBase persistence
├── D_DataProcess/    # MapReduce and local analysis
├── F_ChartsData/     # Visualization data preparation
└── Util/             # HBase and document utilities
```

## Data and Third-Party Content

This project may reference or process data from third-party travel platforms.
Website content, trademarks, datasets, course materials, and external resources
remain under their respective rights and are not covered by this repository's
license. Users are responsible for applicable terms of service, data policies,
and laws when collecting or using external data.

## Project Status

Complete. The repository is preserved for educational and portfolio reference;
it is not under active development.

## License

Copyright 2025 Yixuan Huang

The original code in this repository is distributed under the [MIT License](LICENSE).
Third-party content and data remain under their respective terms.

## Contact

- Website: [yixuanhuang.com](https://yixuanhuang.com)
- Email: [yixnhuang@gmail.com](mailto:yixnhuang@gmail.com)
