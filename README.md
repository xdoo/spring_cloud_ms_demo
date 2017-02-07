# Spring Cloud Microservices Demo

## Technologies used

- [Spring Boot] (https://projects.spring.io/spring-boot/)
- [Spring cloud] (http://projects.spring.io/spring-cloud/) with Zuul, Eureka, Data Stream, etc.
- [Spring Data JPA] (http://projects.spring.io/spring-data-jpa/)
- [Project Lombok] (https://projectlombok.org/)
- [for dependency management and building Apache Maven] (https://maven.apache.org/)
- an IDE ([e.g. Netbeans](http://www.oracle.com/us/technologies/java/jdk-7-netbeans-download-432126.html))

## The big Picture
![Scenario](https://github.com/xdoo/spring_cloud_ms_demo/blob/master/images/Szenario.png)

### Request Scenarios
#### First
![First Request](https://github.com/xdoo/spring_cloud_ms_demo/blob/master/images/First request.png)

#### Second
![Second Request](https://github.com/xdoo/spring_cloud_ms_demo/blob/master/images/Second request.png)

#### Third
![Third Request](https://github.com/xdoo/spring_cloud_ms_demo/blob/master/images/Third request.png)

## Setup your System
### Maven
[Install Apache Maven] (https://maven.apache.org/install.html) on your System. If you behind a [proxy server] (https://maven.apache.org/guides/mini/guide-proxies.html) you have to modify your `{USER_HOME}/.m2/settings.xml` file:

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                  http://maven.apache.org/xsd/settings-1.0.0.xsd">

  <proxies>
    <proxy>
      <active>true</active>
      <protocol>http</protocol>
      <host>{{proxy-ip / proxy-name}}</host>
      <port>{{proxy-port}}</port>
      <nonProxyHosts>localhost</nonProxyHosts>
    </proxy>
  </proxies>

</settings>
```

## do it

### 1. Step: Create Microservice-Projects
To create the microservice-projects use [Spring initializr](http://start.spring.io/). As maven group id we always use 'com.example'. Here's the configuration for the single services (artifact id / dependencies):

- api-gateway / web, actuator, zuul, eureka discovery
- service-registry / actuator, eureka server
- service-a / web, actuator, stream rabbit, h2, jpa, lombok, eureka discovery
- service-b / web, actuator, stream rabbit, h2, jpa, lombok, eureka discovery
- service-c / web, actuator, stream rabbit, h2, jpa, lombok, eureka discovery
- service-d / web, actuator, stream rabbit, h2, jpa, lombok, eureka discovery
- logging / web, actuator, stream rabbit, h2, jpa, lombok

Copy zip files into working directory and unzip.

Additionally you can add an [aggregate pom.xml] (https://github.com/xdoo/spring_cloud_ms_demo/blob/master/pom.xml) to your working directory:
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                      https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
 
  <groupId>com.example</groupId>
  <artifactId>demo</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>pom</packaging>
 
  <modules>
    <module>api-gateway</module>
    <module>service-registry</module>
    <module>service-a</module>
    <module>service-b</module>
    <module>service-c</module>
    <module>service-d</module>
  </modules>
</project>
```

### 2. Step: Open Projects in IDE

Import the maven projects into your IDE. Solve the dependency problems with you IDE (e.g. NetBeans) or plain Maven on the command line. With maven call on each project folder (e.g. {working directory}/service-a) `mvn clean package`. Alternatively you can use the [aggreate pom] (https://github.com/xdoo/spring_cloud_ms_demo/blob/master/pom.xml). Simply switch to your working directory an run on the command line `mvn clean package`.

To test a service start it (over your IDE, command line or maven). 

Maven with plain java on command line:
```bash
$ cd working-directory
$ mvn clean package [creates a target directory inside every project / module folder]
$ cd service-a/target [to this for any service]
$ java -jar run service-a-0.0.1-SNAPSHOT.jar
```

Maven with spring-boot plugin:
```bash
$ cd working-directory/service-a
$ mvn clean spring-boot:run
```

Then you can call an actuator endpoint:
http://docs.spring.io/spring-boot/docs/1.5.1.RELEASE/reference/htmlsingle/#production-ready

### 2. Step: Configure Registry

[docs for Eureka Server] (http://cloud.spring.io/spring-cloud-static/Camden.SR4/#spring-cloud-eureka-server)

[docs for Spring profiles] (https://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#beans-environment)

- Goto service-registry folder / project
- open /src/main/java/com/example/ServiceRegistryApplication.java
- set class annotation @EnableEurekaServer -> [see sample file] (https://github.com/xdoo/spring_cloud_ms_demo/blob/master/service-registry/src/main/java/com/example/Application.java)
- goto /src/main/resources
- delete application.properties
- create file application.yml -> [see sample file] (https://github.com/xdoo/spring_cloud_ms_demo/blob/master/service-registry/src/main/resources/application.yml)

### 3. Step: Register Services with Registry

[docs for Eureka clients] (http://cloud.spring.io/spring-cloud-static/Camden.SR4/#_service_discovery_eureka_clients)

- Goto service-x folder / project
- open /src/main/java/com/example/xApplication.java
- set class annotation @EnableEurekaClient -> [see sample file] (https://github.com/xdoo/spring_cloud_ms_demo/blob/master/service-a/src/main/java/com/example/Application.java)
- goto /src/main/resources
- delete application.properties
- create file application.yml
- rename in application.yml application.name: ServiceX -> [see sample file] (https://github.com/xdoo/spring_cloud_ms_demo/blob/master/service-a/src/main/resources/application.yml)

### 4. Step: Configure API Gateway

[docs for Zuul proxy] (http://cloud.spring.io/spring-cloud-static/Camden.SR4/#_router_and_filter_zuul)

- goto api.gateway folder / project
- open /src/main/java/com/example/ApiGatewayApplication.java
- set class annotation @EnableZuulProxy -> [see sample file] (https://github.com/xdoo/spring_cloud_ms_demo/blob/master/api-gateway/src/main/java/com/example/Application.java)
- goto /src/main/resources
- delete application.properties
- create file application.yml -> [see sample file] (https://github.com/xdoo/spring_cloud_ms_demo/blob/master/api-gateway/src/main/resources/application.yml)

### 5. Step: Implement Rest APIs for Services A + B

[docs for implementing REST APIs with Spring Boot] (http://docs.spring.io/spring-boot/docs/1.5.1.RELEASE/reference/htmlsingle/#howto-write-a-json-rest-service)

[docs for request mappings] (http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html#mvc-ann-requestmapping-composed)

[API docs for request mappings] (http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/bind/annotation/PostMapping.html)

- goto service-a folder / project
- create a file called 'FooController.java' in /src/main/java/com/example
- set class annotation @RestController -> [see sample file] (https://github.com/xdoo/spring_cloud_ms_demo/blob/master/service-a/src/main/java/com/example/FooController.java)
- create a method (e.g. public String hello())
- set method annotation @GetMapping("/hello") -> [see sample file] (https://github.com/xdoo/spring_cloud_ms_demo/blob/master/service-a/src/main/java/com/example/FooController.java)

After a restart of service-a you can test the service.

With curl (command line):
```bash
$ curl localhost:[random_port]/hello
```

Or simply type the url `http://localhost:[random_port]` into your browser. If you can see something like "Hello World", your REST endpoint is working. Now you can try to access the service over the API gateway. Be sure, that the api-gateway service and the registry-service is up and running. Then simply type `http://localhost:8080/servicea/hello` into your browser bar. If you can see "Hello world" again, everything works fine. 

With curl (command line):
```bash
$ curl localhost:8080/servicea/hello
```
Take a look to the docs and try some more coplex samples.

### 6. Step: Implement Outbound Channels for Services A + B

TODO

### 7. Step: Implement Inbound Channels for Service C + D

TODO



