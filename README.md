# Spring Cloud Microservices Demo

## Technologies used

- [Spring Boot] (https://projects.spring.io/spring-boot/)
- [Spring cloud] (http://projects.spring.io/spring-cloud/) with Zuul, Eureka, Data Stream, etc.
- [Spring Data JPA] (http://projects.spring.io/spring-data-jpa/)
- [Project Lombok] (https://projectlombok.org/)

## The big Picture
![Scenario](https://github.com/xdoo/spring_cloud_ms_demo/blob/master/images/Szenario.png)

### Request Scenarios
#### First
![First Request](https://github.com/xdoo/spring_cloud_ms_demo/blob/master/images/First request.png)

#### Second
![Second Request](https://github.com/xdoo/spring_cloud_ms_demo/blob/master/images/Second request.png)

#### Third
![Third Request](https://github.com/xdoo/spring_cloud_ms_demo/blob/master/images/Third request.png)

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

### 2. Step: Configure Registry

TODO

### 3. Step: Register Services with Registry

TODO

### 4. Step: Configure API Gateway

TODO

### 5. Step: Implement Rest APIs for Services A + B

TODO

### 6. Step: Implement Outbound Channels for Services A + B

TODO

### 7. Step: Implement Inbound Channels for Service C + D

TODO



