FROM openjdk:11

ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME

COPY build/libs/*.jar application.jar

EXPOSE 8080

CMD ["java", "-Duser.timezone=\"Asia/Seoul\"", "-jar", "application.jar"]

