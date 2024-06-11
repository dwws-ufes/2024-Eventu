# Base Image: Use the official OpenJDK image with a specific Java version (adjust as needed)
FROM maven:3.9.7-amazoncorretto-21 AS build  

# Environment Variables
ENV APP_HOME=/app
ENV PORT=8080 

# Set Working Directory for Build
WORKDIR $APP_HOME

# Copy Project Files
COPY . .

# Build the Application using Maven
RUN mvn package -DskipTests


# Runtime Image
FROM amazoncorretto:21-alpine-jdk

# Environment Variables
ENV APP_HOME=/app

# Create App Directory (If Needed)
WORKDIR $APP_HOME

# Copy the JAR from the Build Stage (target folder)
COPY --from=build $APP_HOME/target/app-1.0.jar app.jar

# Expose Port
EXPOSE $PORT

# Run the Application
ENTRYPOINT ["java","-jar","/app/app.jar"]