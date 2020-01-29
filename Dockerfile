FROM openjdk:latest
COPY ./target/Coursework_for_Devops.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "Coursework_for_Devops.jar", "db:3306"]