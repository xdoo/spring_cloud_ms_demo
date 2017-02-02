# Spring Cloud Microservices Demo

## Technologies used

- [Spring Boot] (https://projects.spring.io/spring-boot/)
- [Spring cloud] (http://projects.spring.io/spring-cloud/) with Zuul, Eureka, Data Stream, etc.
- [Spring Data JPA] (http://projects.spring.io/spring-data-jpa/)
- [Project Lombok] (https://projectlombok.org/)

## The big Picture
![Scenario](https://github.com/xdoo/spring_cloud_ms_demo/blob/master/images/Szenario.png)

## do it

### 1. Step: Create Microservice-Projects
To create the microservice-projects use [Spring initializr](http://start.spring.io/). As maven group id we always use 'com.example'. Here's the configuration for the single services (artifact id / dependencies):

- api-gateway / web, actuator, zuul
- service-registry / actuator, eureka server
- service-a / web, actuator, stream rabbit, h2, jpa, lombok, eureka discovery
- service-b / web, actuator, stream rabbit, h2, jpa, lombok, eureka discovery
- service-c / actuator, stream rabbit, h2, jpa, lombok, eureka discovery
- service-d / actuator, stream rabbit, h2, jpa, lombok, eureka discovery
- logging / web, actuator, stream rabbit, h2, jpa, lombok
