pipeline {
    agent { dockerfile { filename 'ci/Dockerfile'; args '-u root --entrypoint=""' } }
    options {
        timestamps()
        timeout(time: 45, unit: 'MINUTES')
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '30', artifactDaysToKeepStr: '14'))
    }
    environment { JVM_OPTS = '-Xmx3200m'; TERM = 'dumb' }
    stages {
        stage('Toolchain') {
            steps {
                sh '''
                    set -eu
                    java -version
                    git config --global --add safe.directory "$WORKSPACE"
                    git clean -fdx -q
                    chmod +x gradlew
                '''
            }
        }
        stage('Dependencies') { steps { sh './gradlew dependencies --no-daemon' } }
        stage('Check, test and package') {
            steps { sh './gradlew clean check jar test jacocoTestReport --no-daemon --stacktrace' }
        }
    }
    post {
        always {
            junit allowEmptyResults: true, testResults: 'build/test-results/test/*.xml'
            archiveArtifacts allowEmptyArchive: true, artifacts: 'build/reports/tests/**,build/reports/jacoco/**,build/libs/*.jar'
        }
        success {
            // Keep coverage on Jenkins rather than requiring a new Codecov credential.
            recordCoverage tools: [[parser: 'JACOCO', pattern: 'build/reports/jacoco/test/jacocoTestReport.xml']],
                sourceDirectories: [[path: 'src/main/java']], sourceCodeRetention: 'LAST_BUILD'
        }
        cleanup { sh 'chmod -R a+rwX "$WORKSPACE" 2>/dev/null || true' }
    }
}
