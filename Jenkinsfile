pipeline {
  agent any

  tools {
    maven 'MavenNew'
  }

  environment {
    // MUST match the name you set in Jenkins: Manage Jenkins -> Configure System -> SonarQube servers
    SONARQUBE_SERVER = 'Sonar'

    // Choose a unique key in SonarQube (usually repo name)
    SONAR_PROJECT_KEY = 'mathutils-jenkins'
  }

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Build + Test + Coverage') {
      steps {
        sh 'mvn -version'
        sh 'mvn -B clean verify'
      }
      post {
        always {
          junit '**/target/surefire-reports/*.xml'
          archiveArtifacts artifacts: '**/target/site/jacoco/**', fingerprint: true, allowEmptyArchive: true
        }
      }
    }

    stage('SonarQube Analysis') {
      steps {
        withSonarQubeEnv("${SONARQUBE_SERVER}") {
          sh """
            mvn -B sonar:sonar \
              -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
              -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
          """
        }
      }
    }

    stage('Quality Gate') {
      steps {
        // Fails the pipeline if SonarQube quality gate fails
        timeout(time: 5, unit: 'MINUTES') {
          waitForQualityGate abortPipeline: true
        }
      }
    }

    stage('Package Artifact (JAR)') {
      steps {
        sh 'mvn -B -DskipTests package'
      }
      post {
        success {
          archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true, allowEmptyArchive: true
        }
      }
    }
  }
}
