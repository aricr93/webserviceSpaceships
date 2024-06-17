# Getting Started

### Tutorial to use this webservice

* [Swagger APIs documentation](http://localhost:8080/swagger-ui.html)
* [To run the app use this command line ./mvnw spring-boot:run]
* [To run tests use this command line ./mvnw test]
* [Log file myapp.log located at the root]
* [Flyway library used to manage database scripts located in resources/db/migration, functionality can be checked in the console when starting the app]

### Generate jar and Docker
* [Build a jar ./mvnw clean package]
* [Build the Docker docker build -t spaceships-app .]
* [Run the Docker docker run -p 8080:8080 spaceships-app]
