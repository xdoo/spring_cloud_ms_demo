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

- api-gateway / web, actuator, zuul, eureka discovery
- service-registry / actuator, eureka server
- service-a / web, actuator, stream rabbit, h2, jpa, lombok, eureka discovery
- service-b / web, actuator, stream rabbit, h2, jpa, lombok, eureka discovery
- service-c / actuator, stream rabbit, h2, jpa, lombok, eureka discovery
- service-d / actuator, stream rabbit, h2, jpa, lombok, eureka discovery
- logging / web, actuator, stream rabbit, h2, jpa, lombok

Copy zip files into working directory and unzip.

### 2. Step: Open Projects in IDE

Import the maven projects into your IDE. Solve the dependency problems with

mvn clean package

To test a service start ist (over your IDE or maven). Then you cann call a actuator endpoint:

http://docs.spring.io/spring-boot/docs/1.5.1.RELEASE/reference/htmlsingle/#production-ready

### 2. Step: Configure Registry

docs for Eureka Server:
http://cloud.spring.io/spring-cloud-static/Camden.SR4/#spring-cloud-eureka-server

docs for Spring profiles:
https://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#beans-environment

- Goto service-registry folder / project
- open /src/main/java/com/example/ServiceRegistryApplication.java
- set class annotation @EnableEurekaServer -> [see sample file] (https://github.com/xdoo/spring_cloud_ms_demo/blob/master/service-registry/src/main/java/com/example/Application.java)
- goto /src/main/resources
- delete application.properties
- create file application.yml -> [see sample file] (https://github.com/xdoo/spring_cloud_ms_demo/blob/master/service-registry/src/main/resources/application.yml)

### 3. Step: Register Services with Registry

docs for Eureka clients:
http://cloud.spring.io/spring-cloud-static/Camden.SR4/#_service_discovery_eureka_clients

- Goto service-x folder / project
- open /src/main/java/com/example/xApplication.java
- set class annotation @EnableEurekaClient -> [see sample file] (https://github.com/xdoo/spring_cloud_ms_demo/blob/master/service-a/src/main/java/com/example/Application.java)
- goto /src/main/resources
- delete application.properties
- create file application.yml
- rename in application.yml application.name: ServiceX -> [see sample file] (https://github.com/xdoo/spring_cloud_ms_demo/blob/master/service-a/src/main/resources/application.yml)

### 4. Step: Configure API Gateway

- Goto api.gateway folder / project
- open /src/main/java/com/example/ApiGatewayApplication.java
- set class annotation @EnableZuulProxy -> [see sample file] (https://github.com/xdoo/spring_cloud_ms_demo/blob/master/api-gateway/src/main/java/com/example/Application.java)
- goto /src/main/resources
- delete application.properties
- create file application.yml -> [see sample file] (https://github.com/xdoo/spring_cloud_ms_demo/blob/master/api-gateway/src/main/resources/application.yml)

### 5. Step: Implement Rest APIs for Services A + B

TODO

### 6. Step: Implement Outbound Channels for Services A + B

TODO

### 7. Step: Implement Inbound Channels for Service C + D

TODO



