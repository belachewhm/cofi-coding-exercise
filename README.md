# COFI Coding Exercise

[![Build Status](https://travis-ci.org/belachewhm/cofi-coding-exercise.svg?branch=master)](https://travis-ci.org/belachewhm/cofi-coding-exercise)
[![Coverage Status](https://coveralls.io/repos/github/belachewhm/cofi-coding-exercise/badge.svg?branch=master)](https://coveralls.io/github/belachewhm/cofi-coding-exercise?branch=master)
[![Heroku](http://heroku-badge.herokuapp.com/?app=cofi-coding-exercise&style=flat&svg=1&root=cofi-coding-exercise/swagger-ui.html)](https://cofi-coding-exercise.herokuapp.com/cofi-coding-exercise/swagger-ui.html)

* [Summary](#summary)
* [Tech-Stack](#tech-stack)
* [Build](#build)
* [Run](#run)
* [Swagger Page](#swagger-page)
* [Troubleshooting](#troubleshooting)
* [CICD](#cicd)
* [Contributors](#contributors)

## Summary

This application retrieves pricing data from the Quandl WIKI Stock Price API for a set of securities (COF, GOOGL, MSFT) and given date range (January 1st, 2017 to June 30th, 2017). The application exposes the following APIs that, when triggered, will perform calculations on the data set.
  
<table border="1" style="border: 1px solid #1C6EA4;background-color: #EEEEEE;width: 100%;border-collapse: collapse;text-align: center;">
	<tbody>
		<tr>
			<th>Calculation</th>
			<th>API</th>
			<th>Description</th>
		</tr>
		<tr style="background: #D0E4F5;">
			<td style="padding: 10px;">Average Monthly Open and Close</td>
			<td style="padding: 10px;">/<b>averageMonthlyOpenAndClose</b></td>
			<td style="padding: 10px;">Calculates the average monthly open and close prices for each security for each month of data in the data set</td>
		</tr>
		<tr>
			<td style="padding: 10px;">Average Volume</td>
			<td style="padding: 10px;">/<b>averageVolume</b></td>
			<td style="padding: 10px;">Calculates and returns the average volumes for each security</td>
		</tr>
		<tr style="background: #D0E4F5;">
			<td style="padding: 10px;">Biggest Loser</td>
			<td style="padding: 10px;">/<b>biggestLoser</b></td>
			<td style="padding: 10px;">Calculates and returns which security had the most days where the closing price was lower than the opening price</td>
		</tr>
		<tr>
			<td style="padding: 10px;">Busy Day</td>
			<td style="padding: 10px;">/<b>busyDay</b></td>
			<td style="padding: 10px;">Calculates and returns which days generated unusually high activity for the securities</td>
		</tr>
		<tr style="background: #D0E4F5;">
			<td style="padding: 10px;">Max Daily Profit</td>
			<td style="padding: 10px;">/<b>maxDailyProfit</b></td>
			<td style="padding: 10px;">Calculates and returns which day would provide the highest amount of profit for each security if purchased at the day's low and sold at the day's high</td>
		</tr>
	</tbody>
</table>  

This application also generates a customized Swagger Page that serves as a user interface for the above APIs.

<img width="888px" src="src/main/resources/images/swagger-screenshot.png" title="COFI Coding Exercise Swagger Page" align="center">

## Tech Stack

<p>
	<img height="115" width="275" src="src/main/resources/images/git-logo.png" title="Git">
	<img height="200" width="189" src="src/main/resources/images/java8-logo.png" title="Java 8">
	<img height="100" width="375" src="src/main/resources/images/spring-boot-logo.png" title="Spring/Spring Boot">
	<img height="130" width="375" src="src/main/resources/images/maven-logo.png" title="Apache Maven">
	<img height="100" width="330" src="src/main/resources/images/swagger-logo.png" title="Swagger 2">
</p>

The following technologies were used to build this application:  

* Java (jdk1.8.0_111)
* Spring Boot (v1.5.2.RELEASE)
* Apache Maven (v3.3.9)
* Swagger/Swagger-UI (v2.7.0)
* Git (v2.10.0.windows.1)

## Build

In order to build this application, you must have JDK8 (1.8.0_111 or greater) and Maven (3.3.9 or greater) installed onto your machine. The following are maven build goals that, when invoked from the project's root folder, will build the application.

**Delete the target folder**

	mvn clean
	
**Validate the project is correct and all necessary information is available**

	mvn validate

**Compile the source code of the project into a target folder**

	mvn clean compile
	
**Test the compiled source code using a testing framework**

	mvn clean test

**Take the compiled code and package it in its distributable format, such as a JAR**

	mvn clean package

**Run any checks on results of integration tests to ensure quality criteria are met**

	mvn clean verify

**Install the package into the local repository, for use as a dependency in other projects locally**

	mvn clean install

## Run

Build the project, and use the following command:

	java -jar target/cofi-coding-exercise.jar
	
OR

From the root folder, use the following command:

	mvn spring-boot:run

## Troubleshooting

This application connects to the Quandl WIKI Stock Price API to retrieve data. Connection to this API may be blocked from some firewalled networks (such as a work or school network). If you experience any issues connecting to the API, try to switch onto a different network and try again.

## Swagger Page

Once the application is up and running, you can view the swagger page by navigating to the following link in your browser window:

	http://localhost:8080/cofi-coding-exercise/swagger-ui.html#/

## CICD

I took the liberty of hooking the application up to a (basic) CICD pipeline with Travis CI, Coveralls, and Heroku.

* [Travis CI Build](https://travis-ci.org/belachewhm/cofi-coding-exercise)
* [Coveralls Code Coverage](https://coveralls.io/github/belachewhm/cofi-coding-exercise?branch=master) 
* [Heroku Application](https://cofi-coding-exercise.herokuapp.com/cofi-coding-exercise/swagger-ui.html)

## Contributors

* [Belachew Haile-Mariam](http://www.github.com/belachewhm)
