FROM openjdk:8-jdk

COPY ./target/tasklist-0.0.1-SNAPSHOT.jar /usr/local

ENTRYPOINT ["java", "-jar", "/usr/local/tasklist-0.0.1-SNAPSHOT.jar"]