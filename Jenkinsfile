pipeline {
  agent any
  stages {
    stage('Build') {
      parallel {
        stage('Build') {
          steps {
            echo 'My first job'
          }
        }
        stage('Second') {
          steps {
            echo 'hello'
          }
        }
      }
    }
  }
}