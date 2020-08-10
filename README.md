# Microservices

## Table of contents
* [General info](#general-info)
* [Screen shots](#screenshots)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
![Image](https://i.imgur.com/0exejn8.jpg)   
Webshop API built in microservices architecture integrated with Auth0.

## Screenshots

![Image](https://i.imgur.com/yR1UKLj.png)

## Technologies

Back end:
- Spring Boot, AMQP, Cloud, Data JPA
- RabbitMQ, Hystrix, Feign
- Elasticsearch, PostgreSQL
- Eureka, Zuul
- Hibernate
- Gradle/Maven
- H2, Lombok
- JUnit, Mockito
- Swagger, MapStruct

Front end:
- Angular 8
- Material Angular
- MDBootstrap, Bootstrap


## Setup
### Prerequisites

- Angular 8 or greater is required
```$xslt
$ npm install -g @angular/cli
``` 
- Java 8+

### Deployment

Run back-end with:
```
$ mvn clean install -DskipTests 
$ docker-compose up
```

Run front-end with:
```
$ cd angular-front
$ npm install
$ ng serve
```
Run browser and head to ```http://localhost:4200```
