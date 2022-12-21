# Rest Api Application

### Summary

The assessment consists of an API to be used for opening a new “current account” of already existing
customers.

Requirements:
- The API will expose an endpoint which accepts the customer information (customerID,
initialCredit).
- Once the endpoint is called, a new account will be opened connected to the customer whose ID is
customerID.
- Also, if initialCredit is not 0, a transaction will be sent to the new account.
- Another Endpoint will output the customer information showing Name, Surname, balance, and transactions of the accounts.

Bonuses:
- Accounts and Transactions are different services.
- Frontend (simple one is OK).
- Attention to CI/CD

### The Tech Stack 

- Java v11
- Spring Boot v2.7.5
- Spring Data JPA
- H2 in memory database
- Docker
- Junit
- Restfull Api
- Maven Project 
- Jenknins CI/CD
- Lombok (Additional Library)

### The application Apis

The application has 2 apis
- AccountAPI
- CustomerAPI

```
POST api/v1/createAccount - Creates a new account for an existing customer.
GET  api/v1/customer?surname - Retrieves an existing customer with all transactions.
GET api/v1/customers - Retrieves all customers.
```

### Installation

The repository can be dowloaded via command line:

```git
git clone https://github.com/BardisRenos/RestApplicationCapTest.git
```

In order to clean and install the Maven repository dependencies. 

```
mvn clean install
```

In order to run all test cases of this application.

```
mvn test
```

To execute the project via command line and execute the command

```
mvn spring-boot:run
```

### Application Properties

Changing the server port from 8080 (Default) to 8081. It is needed to change the application.properties file as :

```
server.port=8081
```

Setting the memory database H2 (In our case)

```
# Memory Database for development Environment
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.url=jdbc:h2:mem:applicationdb;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.show-sql=true
spring.datasource.username=sa
spring.datasource.password=
#spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.hibernate.dialect=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create
```

### Layers of the Application

The application is constructed as :

Controller Layer --> Service Layer --> DTO/Mapper Layer --> Repository Layer

<p align="center"> 
<img src="https://github.com/BardisRenos/CapApplication/blob/main/Images/layers.png" width="350" height="450" style=centerme>
</p>

### Docker

It is possible to deploy and run the application via docker container.

```
FROM openjdk:11.0.15-slim
EXPOSE 8081
ADD target/spring-boot-application-docker.jar spring-boot-application-docker.jar
ENTRYPOINT ["java", "-jar", "/spring-boot-application-docker.jar"]
```

In order to build the docker image. This command is needed. 

```
docker build -t spring-boot-application-docker.jar .

```
This image shows the image which has been created after the command.

<p align="center"> 
<img src="https://github.com/BardisRenos/CapApplication/blob/main/Images/dockerImage.png" width="793" height="52" style=centerme>
</p>

Moreover, to run the container into a specific port this command is needed.

```
docker run -p 9090:8081 spring-boot-application-docker.jar

```

<p align="center"> 
<img src="https://github.com/BardisRenos/CapApplication/blob/main/Images/dockerContainer.png" width="793" height="52" style=centerme>
</p>

### Unit Test

The coverage's percentage of the unit tests is 96%.

<p align="center"> 
<img src="https://github.com/BardisRenos/RestApplication/blob/main/Images/coverageTest.png" width="801" height="279" style=centerme>
</p>

### Jenkins

This application has a local CI/CD pipeline from Jenkins. The file which creates the stages.

```
pipeline {
    agent any
    
    stages {
        stage('Build'){
            steps {
                sh './mvnw clean install -DskipTests'
            }
        }
        stage('Tests'){
            steps {
                sh './mvnw test'
            }
        }
    }
}
```

As the below image can shows. The stages of the application.

<p align="center"> 
<img src="https://github.com/BardisRenos/CapApplication/blob/adding/docker_images/Images/jenkins.png" width="861" height="286" style=centerme>
</p>


