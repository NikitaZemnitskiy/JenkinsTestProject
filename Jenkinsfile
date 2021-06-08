pipeline {
    agent any

    tools {
        maven "M3"
    }

    stages {
        stage('Build') {
            steps {
                git 'https://github.com/NikitaZemnitskiy/JenkinsTestProject.git'
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
                telegramSend 'Start?'
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                     telegramSend 'Succes build!'
                }
            }
        }
    }
}