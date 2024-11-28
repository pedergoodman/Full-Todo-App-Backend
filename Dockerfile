# Use an official OpenJDK image as a base
FROM openjdk:17-jdk-alpine

# Set working directory inside the container
WORKDIR /app

# Copy all project files to the container
COPY . ./

# Make the Maven wrapper executable
RUN chmod +x mvnw

# Install dependencies and build the application without running tests
RUN ./mvnw clean package -DskipTests

# Log the contents of the target directory to verify the JAR file
RUN ls -la target/

# Ensure all files have proper permissions
RUN chmod -R 755 /app

# Install bash and gettext (for envsubst)
RUN apk add --no-cache bash gettext

# Copy .env file to the container and set environment variables
COPY .env .env
RUN export $(cat .env | xargs) && \
    envsubst < .env > .env.substituted && \
    mv .env.substituted .env

# Set environment variables using the .env file
ENV $(cat .env | xargs)

# Expose the port the app runs on (change 8080 if you want to use something else, will likely need other)
EXPOSE 8081

# Run the application with the correct JAR file name
CMD ["java", "-jar", "target/Full-Todo-App-Backend-0.0.1-SNAPSHOT.jar"]