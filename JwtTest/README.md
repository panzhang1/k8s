# UserServcie
This is service to access user info

## Usage Guild
- Directly Run
gradle build
gradle bootRun
gradle bootRun --debug-jvm

- Run by Docker
https://spring.io/guides/gs/spring-boot-docker/#initial

gradle build docker
docker run -p 8082:8082 -t pangu/userservice

- Debug for Docker
docker run -e "JAVA_OPTS=-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n" -p 8082:8082 -p 8000:8000 -t pangu/apigateway

- stop Docker service
docker ps
docker stop "container id"

## User Service
- Fetch user Information
/user/{userId}