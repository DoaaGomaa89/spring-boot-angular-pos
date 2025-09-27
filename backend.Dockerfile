# Build Spring Boot (WAR) from ./backend
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /src
COPY backend/pom.xml .
RUN mvn -q -DskipTests dependency:go-offline
COPY backend/ .
RUN mvn -q -DskipTests package

# Run the app
FROM eclipse-temurin:17-jre
WORKDIR /app
# Copy the Spring Boot WAR (this project builds a WAR)
COPY --from=build /src/target/*.war /app/app.war
ENV SERVER_PORT=8081
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/app.war"]
