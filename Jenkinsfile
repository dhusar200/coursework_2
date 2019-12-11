node {
  stage('SCM') {
    checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/dhusar200/coursework_2']]])
  }
  stage('SonarQube analysis') {
    def scannerHome = tool 'SonarQube';
    withSonarQubeEnv(credentialsId: '91986fa0-5bfc-409c-a7d1-b16912fecb3f') {
        sh """cp sonar-server.properties ${scannerHome}conf/sonar-scanner.properties"""
        sh """${scannerHome}bin/sonar-scanner -D sonar.login=admin -D sonar.password=admin"""
    }
  }
}
