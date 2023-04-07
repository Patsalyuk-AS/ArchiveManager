FROM openjdk:17-jdk
MAINTAINER pazalyk@gmail.com
WORKDIR /app
RUN mkdir /app/config
ADD target/*.jar /app/app.jar
EXPOSE 30241
CMD ["java", "-jar", "/app/app.jar"]