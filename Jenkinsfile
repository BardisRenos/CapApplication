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
        stage('Build docker image'){
            steps {
                sh 'docker build -t spring-boot-application-docker.jar .'
            }
        }
    }
}