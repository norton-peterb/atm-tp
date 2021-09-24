# atm-tp
ATM Test Project

Introduction

Application is a Spring Boot web based application with single JAR file
containing all dependencies. Once running application will provide 2 
services required one for balance and the other for withdrawals:

Service URL     Description                 Input                Output
/balance        Get Account Balance         BaseAccountRequest   BalanceQueryResponse
/withdrawFunds  Withdraw Funds from Account WithdrawFundsRequest BalanceQueryResponse

All services use POST method, they also consume and produce JSON data. Error 
messages are included in the JSON response should these occur.

Operation

Application can be built using the included build.gradle by executing the bootJar task.
NOTE: Gradle script is designed to run on Gradle 6.9 and is not yet compatible with Gradle 7
This can then be run on the command line with the command:

java -jar -DSERVER_PORT=8080 atm-test-project.jar

NOTE: This assumes you are using the Docker template application.properties file and 
provides the port number as a property, the JAR will also work in the absence of the 
properties file as 8080 will be chosen as the default port.

Docker

Included is also a Docker build file with a template application properties which allows
the server port to be set as required for the services. The docker build command should be
performed with the following files present in the present working directory:

application.properties - Template Properties
Dockerfile - Docker Build File
atm-test-project.jar - Application JAR file