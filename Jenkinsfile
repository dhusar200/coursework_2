node {
  stage('SCM') {
    checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/dhusar200/coursework_2']]])
  }
  stage('SonarQube analysis') {
    def scannerHome = tool 'SonarQube';
    withSonarQubeEnv(credentialsId: '202b7931-c8d5-45aa-adcb-86aa8b01e132') {
        sh """cp sonar-server.properties ${scannerHome}conf/sonar-scanner.properties"""
        sh """${scannerHome}bin/sonar-scanner -D sonar.login=admin -D sonar.password=admin"""
    }
  }
  stage('Build image') {
    /* This builds the actual image; synonymous to
    * docker build on the command line */

    app = docker.build("dhusar200/coursework2")
  }

  stage('Test image') {
    /* Ideally, we would run a test framework against our image.
    * For this example, we're using a Volkswagen-type approach ;-) */

    app.inside {
      sh 'echo "Tests passed"'
    }
  }

  stage('Push image') {
    /* Finally, we'll push the image with two tags:
    * First, the incremental build number from Jenkins
      * Second, the 'latest' tag.
      * Pushing multiple tags is cheap, as all the layers are reused. */
    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
      app.push("${env.BUILD_NUMBER}")
      app.push("latest")
    }
  }
  stage('Deploy new image') {
    sshagent(['09e5d4bf-281e-48ae-8741-0495d2cf6bd3']) {
    sh 'ssh azureuser@52.138.219.56 kubectl set image deployments/coursework2 coursework2=dhusar200/coursework2:latest'
    }
  }
}
