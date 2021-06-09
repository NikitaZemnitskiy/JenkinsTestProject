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

                    withCredentials([string(credentialsId: 'botSecret', variable: 'TOKEN'), string(credentialsId: 'chatId', variable: 'CHAT_ID')]) {
                          sh  ("""
                              curl -s -X POST https://api.telegram.org/bot${TOKEN}/sendMessage -d chat_id=${CHAT_ID} -d parse_mode=markdown -d text='*${env.JOB_NAME}* : POC *Branch*: ${env.GIT_BRANCH} *Build* : OK *Published* = YES'
                          """)
                          }

                   //  curl -s -X POST https://api.telegram.org/bot1770119692:AAF7wxyRDZ7A-T0AaXT648uwlWK2ndAaVW8/sendMessage -d chat_id=-1001367858798 -d parse_mode=markdown -d text='*TestMessage* : POC *Branch*: Master *Build* : `Aborted` *Published* = `Aborted`'
                }

                 aborted {
                        withCredentials([string(credentialsId: 'botSecret', variable: 'TOKEN'), string(credentialsId: 'chatId', variable: 'CHAT_ID')]) {
                        sh  ("""
                            curl -s -X POST https://api.telegram.org/bot${TOKEN}/sendMessage -d chat_id=${CHAT_ID} -d parse_mode=markdown -d text='*${env.JOB_NAME}* : POC *Branch*: ${env.GIT_BRANCH} *Build* : `Aborted` *Published* = `Aborted`'
                        """)
                        }

                     }

                     failure {
                             withCredentials([string(credentialsId: 'botSecret', variable: 'TOKEN'), string(credentialsId: 'chatId', variable: 'CHAT_ID')]) {
                             sh  ("""
                                 curl -s -X POST https://api.telegram.org/bot${TOKEN}/sendMessage -d chat_id=${CHAT_ID} -d parse_mode=markdown -d text='*${env.JOB_NAME}* : POC  *Branch*: ${env.GIT_BRANCH} *Build* : `not OK` *Published* = `no`'
                             """)
                             }
                          }
            }
        }
    }
}