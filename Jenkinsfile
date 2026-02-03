pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Build + Test') {
      steps {
        sh 'mvn -B clean test'
      }
      post {
        always {
          junit 'target/surefire-reports/*.xml'
        }
      }
    }

    stage('Coverage + Package') {
      steps {
        sh 'mvn -B verify'
      }
      post {
        success {
          archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
          archiveArtifacts artifacts: 'target/site/jacoco/**', fingerprint: true
        }
      }
    }
  }
}
