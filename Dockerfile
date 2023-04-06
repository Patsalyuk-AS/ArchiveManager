FROM openjdk:17-alpine
MAINTAINER pazalyk@gmail.com
WORKDIR /app
ADD /target/*.jar app.jar
EXPOSE 30241
CMD ["java", "-jar", "/app/app.jar"]