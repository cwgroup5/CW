FROM openjdk:latest
COPY ./target/cw-0.1.0.1-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "cw-0.1.0.1-jar-with-dependencies.jar"]