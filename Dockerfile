FROM adoptopenjdk/openjdk11:ubi
VOLUME /tmp
COPY target/CategoryAPI-*.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
