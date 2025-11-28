# Stage 1: Build the WAR file
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn -B -DskipTests clean package

# Stage 2: Run the WAR on Tomcat
FROM tomcat:9-jdk11
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=builder /app/target/*.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
