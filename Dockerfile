FROM ubuntu:20.04

WORKDIR /bot

ENV JAVA_ENV "prod"

RUN apt-get update && apt-get install -y openjdk-17-jre-headless gradle && \
	gradle -d && gradle build && gradle --stop

COPY app/.env .

CMD ["java", "-jar", "app/build/libs/app-all.jar"]
