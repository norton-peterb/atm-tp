FROM openjdk:11
WORKDIR /app
COPY atm-test-project.jar /app/atm-test-project.jar
COPY application.properties /app/application.properties
ENV SERVER_PORT 8080
ENTRYPOINT ["java","-jar","atm-test-project.jar"]