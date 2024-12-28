pipeline{
	
		
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
			steps{
				bat "docker push vivekd999/selenium"
			}
		}
	}
}