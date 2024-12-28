FROM bellsoft/liberica-openjdk-alpine:17.0.12

# Install curl jq
#RUN apk add curl jq
RUN apk add --no-cache curl jq dos2unix


#workspace
WORKDIR home/selenium-docker

#Add required files
ADD target/docker-resources    ./
ADD runner.sh                  runner.sh

# Fix for windows
# Convert to Unix format
RUN dos2unix runner.sh


# Start the runner.sh
#ENTRYPOINT sh runner.sh
ENTRYPOINT ["sh", "runner.sh"]
							  

