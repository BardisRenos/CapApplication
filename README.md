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



### Endpoints

In case docker is used in order to retrieve the data from the (memory) database. It is needed the below path.
After the http it is needed the ip address of the docker container.

```
http://172.17.0.2:8081/api/v1/getUserBySurName?surname=Bardis
```

On the other hand if the applcation run on the local machine, then: 

```
http://localhost:8081/api/v1/getUserBySurName?surname=Bardis
```


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


