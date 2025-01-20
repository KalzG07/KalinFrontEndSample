# KalinFrontEndSample

## Overview

This project outlines some of my automation capabilities using selenium-java
## Features

- List of implementations in this project
- Selenium Java
- Docker
- MySQL
- Testng
- Json
- Extent Report

## Getting Started

### Prerequisites

- List any software or tools required to get the project up and running.
    - Java 11 or later
    - Maven 3.8 or later
    - Docker Compose version v2.31.0-desktop.2


### Breakdown of project structure
The project followed a POM structure, with the majority of locators and methods for a page with a page folder

### notes on files
Most of the methods contain java docs to explain their function.




### Installation

1. Clone this repository:
   git clone https://github.com/KalzG07/KalinFrontEndSample.git
2. Resolve dependencies
3. ensure docker is up using command in terminal: docker-compose up -d
3. Run XML

### Run XML file
Runner name : TestXMLRunners/runAddToCartTests.XML

The runner contains the parameters for chrome and firefox to run concurrently.

### Extent Report
This will generate a breakdown of the run based on the results from the reporting defined in the test actions
INFO, will report information points
Pass, will report passes
softFail, will fail the action but contain with the test if possible.
Fail, will hard fail and report as such.

The report will generate here:
ExtentReports/ExtentReportResults.html


