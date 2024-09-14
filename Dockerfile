FROM eclipse-temurin:21-jdk as build

COPY . /app
WORKDIR /app

RUN chmod +x mvnw
RUN ./mvnw package -DskipTests
RUN mv -f target/*.jar app.jar

FROM eclipse-temurin:21-jre-alpine

ARG PORT
ENV PORT=${PORT}

EXPOSE ${PORT}

COPY --from=build /app/app.jar .

RUN addgroup -S runtime && adduser -S runtime -G runtime
USER runtime

ENTRYPOINT [ "java", "-Dserver.port=${PORT}", "-jar", "app.jar" ]
