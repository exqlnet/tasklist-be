FROM maven:3.6-jdk-8

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone


COPY . /tasklist-be
WORKDIR /tasklist-be
COPY ./settings.xml /root/.m2/
RUN mvn package -Dmaven.test.skip=true
ENTRYPOINT ["java", "-jar", "/tasklist-be/target/tasklist-0.0.1-SNAPSHOT.jar"]