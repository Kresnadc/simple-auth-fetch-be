# Simple Fetch

Requirement:
- Java 11
- H2 Database
- Docker

# Docker Containerize

### Containerize with Dockerfile
Make sure `Dockerfile` exists

Run command: 
```
docker build --build-arg JAR_FILE=build/libs/\*.jar -t springio/gs-spring-boot-docker .
```

or 

### Build a Docker Image with Gradle

```
./gradlew bootBuildImage --imageName=springio/gs-spring-boot-docker
```

Then run 
```
docker run -p 8080:8080 -t springio/gs-spring-boot-docker
```