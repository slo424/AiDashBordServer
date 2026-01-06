# AI Dashboard Server

This is the backend server of the AI Dashboard project.

The prject consists of API endpoints for getting the statistic data for AI Platform uage.

Features such as view by team and view by a given date range have been implemented and tested.

As the document given for this project, the main entry point of the APIs is "api" and the main API endpoint are:
  - "/usage" - a Get API with no path variable and no request body, this API gets and returns all AI usage data from the past 7 days
  - "/usage{teamId}" - a GET API with a required empty request body, this API gets and returns all AI usage data for the given team ID from the past 7 days
