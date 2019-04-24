# Spring Boot Blueprint
This project is a template based on Spring Boot for REST applications. 

It already includes the following: 
- Employee entity
- Repository layer for the entity
- Service layer for the entity
- Rest controller for CRUD-operations regarding the entity
- Unit and Integration tests for all layers

For a quick start, just fill in the following properties in the application.properties file:
- spring.datasource.username
- spring.datasource.password
- spring.datasource.url

The included pom.xml contains the following dependencies:
- Spring Boot Starter
  - Spring Boot Starter Data JPA
  - Spring Boot Starter Web
  - Spring Boot DevTools
- Other
  - MySQL Connector
  - Lombok
  - Jackson Dataformat XML
  - ModelMapper
  - Jetbrains Annotations
  - Guava
- Tests
  - Spring Boot Starter Test
  - JUnit Jupiter
  - H2-Database
  - RestAssured
- Code analysis
  - Findbugs
  - PMD
  - Checkstyle
  
Other nice things are: 
- pre-configured xml configuration file for logback
- Database initializer to populate the database with sample values
- application properties file for development und test 