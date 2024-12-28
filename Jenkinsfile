pipeline{
	
	agent any
		
	stages{
		
		stage('Build jar'){
			steps{
				bat "mvn clean package -DskipTests"
			}
		}
		
		stage('Build Docker Image'){
			steps{
				bat "docker build -t=vivekd999/selenium ."
			}
		}
		
		stage('Push Image'){
			
			environment{
				DOCKER_HUB=credentials('dockerhub-cred')
			}
			steps{
				bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
				//docker login -u ${DOCKER_HUB_USR} -p ${DOCKER_HUB_PSW}  - for MAC
				bat "docker push vivekd999/selenium"
			}
		}
	}
	post{
		always{
			bat "docker logout"
		}
	}
}