pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'My first job'
        readFile 'workspace\\Selenium_master\\test-output\\index.html'
      }
    }
  }
}