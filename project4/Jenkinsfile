pipeline {
    agent { docker { image 'maven:3.1.1' } }
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
            }
        }
    }
}