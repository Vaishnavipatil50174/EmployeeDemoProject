pipeline {
    agent any

    tools {
        maven 'Maven3'  // Use configured Maven installation
        jdk 'JDK17'     // Use configured JDK installation
    }

    environment {
        MVN_CMD = "mvn"
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/Vaishnavipatil50174/EmployeeDemoProject.git'
            }
        }

        stage('Build') {
            steps {
                bat "${MVN_CMD} clean compile"
            }
        }

        stage('Test') {
            steps {
                bat "${MVN_CMD} test"
            }
        }

        stage('Package') {
            steps {
                bat "${MVN_CMD} package -DskipTests"
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
