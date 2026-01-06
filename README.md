# AI Dashboard Server

This is the backend server of the AI Dashboard project.

The prject consists of API endpoints for getting the statistic data for AI Platform uage.

Features such as view by team and view by a given date range have been implemented and tested.

As the document given for this project, the main entry point of the APIs is "api" and the main API endpoint are:
  - "/usage" - a Get API with no path variable and no request body, this API gets and returns all AI usage data from the past 7 days
  - "/usage{teamId}" - a GET API with a required empty request body, this API gets and returns all AI usage data for the given team ID from the past 7 days

#####   additional functionalities and APIs are also implemented as helper functions and to be used by the frontend web application

- This project is implemented with IntelliJ, Java 19, Spring framework, and Maven
- A set of sample AI usage data is used, and the results shown in this project is computed around this data
- Errors and exceptions are gracefully handled to avoid crashing
- 2 JUnit test cases are included

## How I designed and approached this project
- As sorting and filtering play a big part of this project, I have been keeping these in mind when designing the AIUsage model
- Although some preparation work for sorting and filter are done in the RestController, the more complicated work is done with each command
- The start and end filtering date range since the same calculation is needed for the basic 7 days filter
- Although only one API is required, I have implemented a couple more to handle different situations such as showing all teams, an indiviual team, and with a combination of date range

## How to Run
- As the project is built with IntelliJ, it is exepcted to be imported and run
- Before running and starting the AiDashboardApplication, please be sure to reload with Maven to install all the required dependencies
- The AiDashboardApplication can then be, and it will be running on Port 8080 by default
- when testing locally to a GET endpoint with URL like "http://localhost:8080/api/usage/"

An assumption that I have made is that there are no AI data entries as there is no functionalities for getting them nor is it reqired. A set of AI Usage data is hardcoded in the system.
