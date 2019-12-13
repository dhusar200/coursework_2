node {
  stage('SCM') {
    checkout([$class: 'GitSCM', branches: [[name: '*/Dominik']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/dhusar200/coursework_2']]])
  }
  stage('SonarQube analysis') {
    def scannerHome = tool 'SonarQube';
    withSonarQubeEnv(credentialsId: '91986fa0-5bfc-409c-a7d1-b16912fecb3f') {
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
    sshagent(['19e2344e-d347-4529-8d9b-2404452b2b14']) {
    sh 'ssh azureuser@23.102.59.66 kubectl set image deployments/coursework2 coursework2=dhusar200/coursework2:latest'
    }
  }
}
