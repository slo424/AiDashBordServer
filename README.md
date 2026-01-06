# AI Dashboard Server

This is the backend server of the AI Dashboard project.

The prject consists of API endpoints for getting the statistic data for AI Platform uage.

Features such as view by team and view by a given date range have been implemented and tested.

As the document given for this project, the main entry point of the APIs is "api" and the main API endpoint are:
  - "/usage" - a Get API with no path variable and no request body, this API gets and returns all AI usage data from the past 7 days
  - "/usage{teamId}" - a GET API with a required empty request body, this API gets and returns all AI usage data for the given team ID from the past 7 days

#####   additional functionalities and APIs are also implemented as helper functions and to be used by the frontend web application

- This project is implemented with IntelliJ, Java 21, Spring framework, and Maven
- A set of sample AI usage data is used, and the results shown in this project is computed around this data
- Errors and exceptions are gracefully handled to avoid crashing

## How to Run
- As the project is built with IntelliJ, it is exepcted to be imported and run
- Before running and starting the AiDashboardApplication, please be sure to reload with Maven to install all the required dependencies
- The AiDashboardApplication can then be, and it will be running on Port 8080 by default
- when testing locally to a GET endpoint with URL like "http://localhost:8080/api/usage/"

An assumption that I have made is that there are no AI data entries as there is no functionalities for getting them nor is it reqired. A set of AI Usage data is hardcoded in the system.
