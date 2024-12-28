Docker command to create volume mapping from docker container to local:
 docker run -it -v ${PWD}/result:/home/selenium-docker/test-output vivekd999/selenium-docker:v1
 
 # To RUN selenium-docker using ip address
  java -Dselenium.grid.enabled=true -Dselenium.grid.hubHost=192.168.100.14 -cp 'libs/*' org.testng.TestNG test-suites/vendor-portal.xml

  #combining above 2 commands
  docker run -it -v ${PWD}/result:/home/selenium-docker/test-output vivekd999/selenium-docker:v1 # java -Dselenium.grid.enabled=true -Dselenium.grid.hubHost=192.168.100.14 -cp 'libs/*' org.testng.TestNG test-suites/vendor-portal.xml
  
  # if we write entry point like this in Docker file USING ENVIRONMENT VARIABLES
     ENTRYPOINT java -cp 'libs/*' -Dselenium.grid.enabled=true \
						      -Dselenium.grid.hubHost=${HUB_HOST} \
							  -Dbrowser=${BROWSER} \
							  org.testng.TestNG \
							  -threadcount ${THREAD_COUNT} \
							  test-suites/${TEST_SUITE}
	Then we need to use below command to RUN Tests by  USING ENVIRONMENT VARIABLES
	 docker run -e BROWSER=chrome -e HUB_HOST=192.168.100.14 -e TEST_SUITE=vendor-portal.xml -e THREAD_COUNT=4 vivekd999/selenium-docker:v1
							  