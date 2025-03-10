# Build stage
FROM maven:3.8.4-openjdk-8 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:8-jre-slim
WORKDIR /app
COPY --from=build /app/target/reporter-1.0.0-jar-with-dependencies.jar app.jar

# Environment variables
ENV DB_HOST=mysql
ENV DB_PORT=3306
ENV DB_NAME=reporter
ENV DB_USERNAME=reporter
ENV DB_PASSWORD=reporter123
ENV ORGANIZATION_API_KEY=your_api_key
ENV ORGANIZATION_API_URL=your_api_url
ENV ORGANIZATION_API_PUBLIC_KEY=your_public_key
ENV ORGANIZATION_API_PRIVATE_KEY=your_private_key

EXPOSE 9100
ENTRYPOINT ["java", "-jar", "app.jar"] 