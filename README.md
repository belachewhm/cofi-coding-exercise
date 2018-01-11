# COFI_Coding_Exercise

An application that retrieves pricing data from the Quandl WIKI Stock Price API for a given set of securities and date range, and displays various information about the data retrieved

* [Summary](#summary)
* [Tech-Stack](#tech-stack)
* [Build](#build)
* [Run](#run)
* [Contributors](#contributors)

## Summary

## Tech Stack

<p>
	<img height="115" width="275" src="src/main/resources/images/git-logo.png" title="Git">
	<img height="200" width="189" src="src/main/resources/images/java8-logo.png" title="Java 8">
	<img height="100" width="375" src="src/main/resources/images/spring-boot-logo.png" title="Spring/Spring Boot">
	<img height="130" width="375" src="src/main/resources/images/maven-logo.png" title="Apache Maven">
	<!-- <img height="130" width="155" src="src/main/resources/images/docker-logo.png" title="Docker"> -->
	<img height="100" width="330" src="src/main/resources/images/swagger-logo.png" title="Swagger 2">
</p>

## Build

Delete the target folder

	mvn clean
	
Validate the project is correct and all necessary information is available

	mvn validate

Compile the source code of the project into a target folder

	mvn clean compile
	
Test the compiled source code using a testing framework

	mvn clean test

Take the compiled code and package it in its distributable format, such as a JAR

	mvn clean package

Run any checks on results of integration tests to ensure quality criteria are met

	mvn clean verify

Install the package into the local repository, for use as a dependency in other projects locally

	mvn clean install

## Run

Build the project, navigate to the target folder (or wherever the runnable jar file is located), and use the following command

	java -jar cofi-coding-exercise.jar
	
OR

From the root folder, run the following command

	mvn spring-boot:run

## Contributors

* [Belachew Haile-Mariam](http://www.github.com/belachewhm)