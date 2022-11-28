FROM adoptopenjdk/openjdk11:jre-11.0.9_11.1-alpine
VOLUME /tmp
EXPOSE 8080
ADD build/libs/mentomen-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=prod","-jar","/app.jar"]