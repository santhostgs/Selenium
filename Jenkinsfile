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
            sh 'curl http://10.51.23.244:8080/job/test/build?token=RUNROBOT1'
          }
        }
      }
    }
  }
}