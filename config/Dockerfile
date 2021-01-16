FROM openjdk:14-alpine
VOLUME /tmp
COPY build/libs/config-0.0.1-SNAPSHOT.jar ConfigServer.jar
ENTRYPOINT ["java","-jar","ConfigServer.jar"]