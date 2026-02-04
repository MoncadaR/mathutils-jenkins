pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Build + Test + Coverage + Package') {
      steps {
        sh 'mvn -B clean verify'
      }
      post {
        always {
          junit '**/target/surefire-reports/*.xml'
          archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true, allowEmptyArchive: true
          archiveArtifacts artifacts: '**/target/site/jacoco/**', fingerprint: true, allowEmptyArchive: true
        }
      }
    }
  }
}
