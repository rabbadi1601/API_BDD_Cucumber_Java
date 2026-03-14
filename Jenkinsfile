pipeline {
    agent any

    triggers {
        cron('0 7 * * *')  // Schedule to run every day at 7:00 AM
    }
    tools {
            jdk 'JAVA17'
        }

    stages {
        stage('Build and Test') {
            steps {
                // Run Maven clean and test
                sh 'mvn clean test'
            }
        }

        stage('Generate Allure Report') {
            steps {
                // Generate Allure report (requires Allure Jenkins plugin)
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            // Publish Cucumber HTML report
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target',
                reportFiles: 'cucumber-reports.html',
                reportName: 'Cucumber Report',
                reportTitles: ''
            ])
            // Archive test results
            junit 'target/surefire-reports/*.xml'
        }
        failure {
            // Notify on failure (optional, configure as needed)
            echo 'Build failed!'
        }
    }
}
