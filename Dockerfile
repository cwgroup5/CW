FROM openjdk:latest
COPY ./target/courseworkG5-0.1.0.1.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "courseworkG5-0.1.0.1.jar"]