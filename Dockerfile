FROM openjdk:8-jdk

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN apt-get update && apt-get install maven
RUN mvn package build


ENTRYPOINT ["java", "-jar", "./target/tasklist-0.0.1-SNAPSHOT.jar"]