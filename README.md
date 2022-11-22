# CapApplication

### Info

The assessment consists of an API to be used for opening a new “current account” of already existing
customers.

Requirements:
- The API will expose an endpoint which accepts the user information (customerID,
initialCredit).
- Once the endpoint is called, a new account will be opened connected to the user whose ID is
customerID.
- Also, if initialCredit is not 0, a transaction will be sent to the new account.
- Another Endpoint will output the user information showing Name, Surname, balance, and transactions of the accounts.

Bonuses:
- Accounts and Transactions are different services.
- Frontend (simple one is OK).
- Attention to CI/CD

### Prerequisites 

- Java v11
- Spring Boot v2.7.5
- Maven Project 
- Maven Build Tool
- Lombok (Additional Library)

### Installation

The repository can be dowloaded via command line:

```git
git clone https://github.com/BardisRenos/CapApplication.git
```

In order to clean and install the Maven repository dependencies. 

```
mvn clean install
```

In order to run all test cases of this application.

```
mvn test
```

### Application Properties

Changing the server port from 8080 (Default) to 8081. It is needed to change the application.properties file as :

```
server.port=8081
```

### Docker

It is possible to deploy and run the application via docker container.

```Docker
FROM openjdk:11.0.15-slim
EXPOSE 8081
ADD target/spring-boot-application-docker.jar spring-boot-application-docker.jar
ENTRYPOINT ["java", "-jar", "/spring-boot-application-docker.jar"]
```

