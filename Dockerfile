FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml ./

RUN mvn dependency:go-offline


COPY src ./src

RUN mvn package -DskipTests





FROM eclipse-temurin:21-jre-alpine AS usuario

WORKDIR /app

COPY --from=builder /app/target/avaliador-0.0.1-SNAPSHOT.jar /app/avaliador.jar

EXPOSE 8080

CMD [ "java", "-jar", "avaliador.jar" ]
