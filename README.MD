# Full Todo App Backend - Setup and Run Guide

This guide will help you set up and run the backend for the Full Todo App. Follow these steps to ensure your environment is properly configured.

## Table of Contents
1. Prerequisites
2. Environment Setup
   - Windows (PowerShell)
   - macOS/Linux (Terminal)
3. Running the Backend
   - Building and Running
4. Running with Docker
5. Running with Docker Compose
6. Verifying Installation
7. Troubleshooting

---

## Prerequisites

- **Java 17 or higher**
- **Maven** (for building the application)
- **Okta Developer Account** (for OAuth2 authentication)
- **Database** (H2 is used by default, but PostgreSQL/MySQL or equivalent can be used for production)
- **Docker** (for containerized deployment)
- **Docker Compose** (optional for multi-container setup)

## Environment Setup

Before running the application, you need to set some environment variables for Okta OAuth2, server port, and optional database configuration.

### Windows (PowerShell)

1. Open PowerShell.
2. Navigate to the backend project directory.
3. Set the required environment variables using the following commands:

   ```powershell
   # Database Configuration
   $env:DB_URL="jdbc:h2:mem:fulltodoappdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE"
   $env:DB_DRIVER="org.h2.Driver"
   $env:DB_USERNAME="sa"
   $env:DB_PASSWORD=""
   $env:DB_DDL_AUTO="update"
   $env:DB_DIALECT="org.hibernate.dialect.H2Dialect"

   # Okta OAuth2 Configuration
   $env:OKTA_OAUTH2_ISSUER="https://your-okta-issuer-domain/oauth2/default"
   $env:OKTA_OAUTH2_CLIENT_ID="your-okta-client-id"
   $env:OKTA_OAUTH2_CLIENT_SECRET="your-okta-client-secret"
   $env:OKTA_OAUTH2_SCOPES="openid,profile,offline_access"

   # Server Port Configuration
   $env:SERVER_PORT="8081"
   ```

   Replace `your-okta-issuer-domain`, `your-okta-client-id`, `your-okta-client-secret` with the actual values from your Okta developer console.

### macOS/Linux (Terminal)

1. Open Terminal.
2. Navigate to the backend project directory.
3. Set the required environment variables using the following commands:

   ```bash
   # Database Configuration
   export DB_URL="jdbc:h2:mem:fulltodoappdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE"
   export DB_DRIVER="org.h2.Driver"
   export DB_USERNAME="sa"
   export DB_PASSWORD=""
   export DB_DDL_AUTO="update"
   export DB_DIALECT="org.hibernate.dialect.H2Dialect"

   # Okta OAuth2 Configuration
   export OKTA_OAUTH2_ISSUER="https://your-okta-issuer-domain/oauth2/default"
   export OKTA_OAUTH2_CLIENT_ID="your-okta-client-id"
   export OKTA_OAUTH2_CLIENT_SECRET="your-okta-client-secret"
   export OKTA_OAUTH2_SCOPES="openid,profile,offline_access"

   # Server Port Configuration
   export SERVER_PORT="8081"
   ```

   Replace `your-okta-issuer-domain`, `your-okta-client-id`, `your-okta-client-secret` with the actual values from your Okta developer console.

## Running the Backend

### Building and Running

Once the environment variables are configured, you can proceed to build and run the backend.

1. Navigate to the project directory in your Terminal (macOS/Linux) or PowerShell (Windows).
2. Run the following command to build the project:

   ```bash
   ./mvnw clean install -DskipTests
   ```

3. After a successful build, run the application:

   ```bash
   ./mvnw spring-boot:run
   ```

   The application should start and be accessible on `http://localhost:8081`.

## Running with Docker

To run the backend as a Docker container:

1. Ensure Docker is installed and running on your machine.
2. Build the Docker image by running the following command in the backend directory:

   ```bash
   docker build -t full-todo-backend .
   ```

3. Once the image is built, run the container:

   ```bash
   docker run -p 8081:8081 --env-file .env full-todo-backend
   ```

   Make sure the `.env` file is properly configured with the necessary environment variables, similar to the setup instructions above.

## Running with Docker Compose

If you want to run both the backend and frontend together using Docker Compose, follow these steps:

1. Create a `docker-compose.yml` file in the root directory of your project (or adjust paths as necessary).

   ```yaml
   version: '3.8'

   services:
     backend:
       build:
         context: ../Full-Todo-App-Backend  # Adjust path accordingly
         dockerfile: Dockerfile
       ports:
         - "8081:8081"  # Use port 8081 instead of 8080
       env_file:
         - ../Full-Todo-App-Backend/.env  # Use the .env file for environment variables

     frontend:
       build:
         context: ../full-todo-app-frontend  # Adjust path accordingly
         dockerfile: Dockerfile
       ports:
         - "3000:80"  # Map to port 3000 for frontend development consistency
       depends_on:
         - backend
       env_file:
         - ../full-todo-app-frontend/.env  # Use the .env file for frontend variables
   ```

2. Run the Docker Compose setup:

   ```bash
   docker-compose up --build
   ```

   This command will build and run both the backend and frontend containers, making them available on their respective ports.

## Verifying Installation

To verify that everything is working correctly, follow these steps:

1. **Access the Login Page**: Open a web browser and go to `http://localhost:8081/login`. You should see the login screen that uses Okta for authentication.
2. **Access Protected Routes**: After logging in, attempt to navigate to a protected page (e.g., `/protected`). You should only be able to access this route if you are authenticated.
3. **Check API Endpoints**: Verify that the backend API endpoints are functioning as expected by sending test requests using tools like Postman or Curl.

## Troubleshooting

- **Port Conflicts**: Ensure that the `SERVER_PORT` is not already in use by another application. If there is a conflict, change the value of `SERVER_PORT` in your environment variables.
- **CSRF Token Issues**: If you are experiencing CSRF token-related issues, make sure that your frontend application is properly retrieving and sending the CSRF token. Check the network tab in your browser's developer tools to ensure that the token is being included in your requests.
- **Redirect Loop Errors**: A common issue can be infinite redirects when attempting to access protected routes after logging out. This usually happens if there is a misconfiguration in the security setup, particularly in the `SecurityConfiguration` class. Verify that the logout and login pages are configured correctly and that unauthorized users are redirected properly.
- **Okta Configuration**: Ensure that your Okta OAuth2 client ID and secret are correctly configured, and that the redirect URIs set in Okta match what your backend expects.

---

If you encounter any other issues, consult the official Spring Security and Okta documentation for additional guidance.
