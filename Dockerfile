FROM openjdk:11.0.15-slim
LABEL maintainer="Renard (Renos) Bardis"
EXPOSE 8081 8081
ADD target/spring-boot-application-docker.jar spring-boot-application-docker.jar
ADD run.sh run.sh
#CMD ["java", "-jar", "/spring-boot-application-docker.jar"]
ENTRYPOINT ["sh", "/run.sh"]