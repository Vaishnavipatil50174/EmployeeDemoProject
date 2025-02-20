pipeline {
    agent any  // Run on any available Jenkins agent

    tools {
        maven 'Maven3'  // Use configured Maven installation
        jdk 'JDK17'     // Use configured JDK installation
    }

    environment {
        MVN_CMD = "mvn"  // Define Maven command variable
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/user/spring-boot-app.git'
            }
        }

        stage('Build') {
            steps {
                sh "${MVN_CMD} clean compile"
            }
        }

        stage('Test') {
            steps {
                sh "${MVN_CMD} test"
            }
        }

        stage('Package') {
            steps {
                sh "${MVN_CMD} package -DskipTests"
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
    }

    post {
        success {
            echo '✅ Build & Test Successful'
        }
        failure {
            echo '❌ Build Failed'
        }
    }
}
