# Charter

This is an assignment for Charter. Goal of the assignment is to create an application will perform the following logic -

- A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
- Given a record of every transaction during a three month period, calculate the reward points earned for each customer per month and total.
- Make up a data set to best demonstrate your solution

## Relevant notes about the solution

- Receives a request on a REST API with the customer id for whom the points need to be added up. 
- Reads all the transactions for the customer from a table (hosted in an in-memory H2 database).
- For each transaction, calculate the points and add up for all transactions.
- Return the total points to the requestor.

## Broad Steps to run (see below for details) - 

- Spin up using "gradlew bootRun"
- Go to H2 console (http://localhost:8080/h2-console); Make sure JDBC URL is jdbc:h2:mem:testdb
  - Create table; Insert transaction records (See SQLs below)
- Invoke the "calculatePointsForCustomer" REST endpoint.
  http://localhost:8080/rest/points/calculatePointsForCustomer?customerId=C001

## History

4/26/2021  1.0.0   Started work. Created Git repository
4/26/2021  1.1.0   Created Point calculator Service
4/28/2021  1.2.0   Created REST endpoint; Added JUnit test 
                   Note: Only one UT is created. Most other methods will require mocking of interfaces, that will involve more work.
                   Did some refactoring

## Technology Highlights -

- Works with Open JDK 15 from Adopt
- SpringBoot (See version in build.gradle)
- Gradle (See version in gradle/wrapper/gradle-wrapper.properties)
- Spring JPA - Hibernate - Transaction
- Has Spring JPA configuration for an embedded database (H2).
- RESTController (Returns a JSON response)
- MVCController (Forwards to a view that uses Thymeleaf to build an HTML page using templates in /src/main/resources/templates)
- Creates Boot Jar (build target - bootJar)
- Spring Session (MvcSessionController.java) - Uses Redis for caching
- Redis (See configuration in application.properties / application.yml).
  Currently, disabled. If needed, change store-type to "redis" and make sure Redis is running.
- Spring Security [Password in appplication.yml]
    Log in screen pops up for web requests
    For /rest URL's, no login required (Configured in SecurityConfig.java)

## Issues

## Gradle tasks

gradlew eclipse  - Set up Eclipse environment. 
>IMPORTANT - DO not import as General Project.
>Import as Gradle Project into Spring TS. Add Gradle nature to project. 

gradlew bootRun  - Spin up the application in a Tomcat server. 

gradlew bootJar - Create a FAT JAR in C:\Temp\Builds\charter\libs directory. 
>This JAR will have embedded app server

## application.yml

- Listener Port Number is given by server.port
- Spring Security password is spring.security.user.password

## Caching - JCache & EHCache

Read - https://howtodoinjava.com/spring-boot2/ehcache3-config-example/

Dependencies : spring-boot-starter-cache, hibernate-jacache, ehcache, caceh-api, jaxb-core, jaxb-impl.  
CustomCachEventLogger.java has logic to log cache events.  
Look for -

- Properties in application.yml : spring.cache.*, spring.jpa.properties.hibernate.cache.*, spring.jpa.properties.net.*
- @EnableCaching annotation in Application.java
- @Cacheable annotation in Db01Service.java
  
## Pre-Reqs before Running Application

### Redis Server

Requires a Redis Server for session caching. Change configuration (port number etc) if necessary
Start Redis Server on WSL Ubuntu - "sudo service redis-server start"  (Password - "eldho")

## TO RUN Application

### To Run on Command Prompt

Start up using "gradlew bootRun". All environment variables are set in build.gradle.

### To Run in STS/Eclipse

Right-Click on application and run as Spring Boot App
On Run-Configuration, add these VM arguments - 
>-DLOG4J_LEVEL=info -DLOG4J_PATH=C:/Temp/Logs/charter -Dspring.profiles.active=desktop

### To Run the Fat Jar (Created by gradlew bootJar)

java -DLOG4J_LEVEL=info -DLOG4J_PATH=C:/Temp/Logs/charter -Dspring.profiles.active=<profile> -jar <projectName>-?.?.?.jar   
*where profile is the spring profile from application.yml*  
Note - System properties (-D) must be supplied before the JAR file on this command line.


## When the application is up

### H2 Embedded database Console (For datasource DB01)

See application.yml for Data sources.

http://localhost:8080/h2-console  (See SQL above)
*Ensure that the JDBC URL matches the JDBC URL in YML file*  

Run this SQL -

    DROP TABLE TRANSACTIONS;

    CREATE TABLE TRANSACTIONS
    (TXN_ID           INTEGER,
     CUSTOMER_ID      VARCHAR2(5),
     PURCHASE_AMOUNT  DECIMAL);

    INSERT INTO TRANSACTIONS (TXN_ID, CUSTOMER_ID, PURCHASE_AMOUNT) values (1, 'C001', 220.00);
    INSERT INTO TRANSACTIONS (TXN_ID, CUSTOMER_ID, PURCHASE_AMOUNT) values (2, 'C001', 1000.00);
    INSERT INTO TRANSACTIONS (TXN_ID, CUSTOMER_ID, PURCHASE_AMOUNT) values (3, 'C001', 2200.00);
    INSERT INTO TRANSACTIONS (TXN_ID, CUSTOMER_ID, PURCHASE_AMOUNT) values (4, 'C001', 75.00);
    INSERT INTO TRANSACTIONS (TXN_ID, CUSTOMER_ID, PURCHASE_AMOUNT) values (5, 'C002', 110.00);
    INSERT INTO TRANSACTIONS (TXN_ID, CUSTOMER_ID, PURCHASE_AMOUNT) values (6, 'C002', 320.00);
    INSERT INTO TRANSACTIONS (TXN_ID, CUSTOMER_ID, PURCHASE_AMOUNT) values (7, 'C002', 520.00);
    INSERT INTO TRANSACTIONS (TXN_ID, CUSTOMER_ID, PURCHASE_AMOUNT) values (8, 'C002', 820.00);

## Service Endpoints and operations

Port and Context-Path (/) are in application.yml for each env

### REST Controllers

http://localhost:8080/rest/points/calculatePointsForPurchase?purchaseAmount=200.00 : Calculate points for a purchase amount
http://localhost:8080/rest/points/calculatePointsForCustomer?customerId=C001 : Calculate and add up points for a customer

### MVC Controller

(Id: User; Password: password)

http://localhost:8080/mvc/hello/hello1 : Forwards to hello.html (gets message from application.properties).  
http://localhost:8080/mvc/hello/hello2?message=Eldho : Forwards to hello.html (message is received as request param).  
http://localhost:8080/mvc/hello/hello3 : Shows how to get HTTP (Request/Response/Session) objects.  

### MVC Controller (Vue Test)

http://localhost:8080/mvc/vue/test - A basic Vue test (https://www.baeldung.com/spring-boot-vue-js)

### MVC Controller (Spring Session Management) :-

http://localhost:8080/mvc/session : Home page for MVC Session testing. Creates a session and caches user attributes in Redis.  

## Actuator Endpoints

http://localhost:8080/actuator/health  
http://localhost:8080/actuator/info  
