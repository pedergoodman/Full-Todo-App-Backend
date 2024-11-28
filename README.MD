# Full Todo App Backend - Setup and Run Guide

This guide will help you set up and run the backend for the Full Todo App. Follow these steps to ensure your environment is properly configured.

## Table of Contents
1. Prerequisites
2. Environment Setup
   - Windows (PowerShell)
   - macOS/Linux (Terminal)
3. Running the Backend
   - Building and Running
4. Verifying Installation
5. Troubleshooting

---

## Prerequisites

- Java 17 or higher
- Maven (for building the application)
- Okta Developer Account (for OAuth2 authentication)
- A Database (PostgreSQL/MySQL or equivalent, if required)

## Environment Setup

Before running the application, you need to set some environment variables for Okta OAuth2, server port, and optional database configuration.

### Windows (PowerShell)

1. Open PowerShell.
2. Navigate to the backend project directory.
3. Set the required environment variables using the following commands:

   ```powershell
   # Okta OAuth2 Configuration
   $env:OKTA_OAUTH2_ISSUER="https://dev-74889125.okta.com/oauth2/default"
   $env:OKTA_OAUTH2_CLIENT_ID="your-okta-client-id"
   $env:OKTA_OAUTH2_CLIENT_SECRET="your-okta-client-secret"
   $env:OKTA_OAUTH2_SCOPES="openid,profile,offline_access"

   # Server Port Configuration
   $env:SERVER_PORT=8080

   # Optional: Database Configuration
   $env:DATABASE_URL="your-database-url"
   ```

   Replace `your-okta-client-id`, `your-okta-client-secret`, and `your-database-url` with the actual values.

### macOS/Linux (Terminal)

1. Open Terminal.
2. Navigate to the backend project directory.
3. Set the required environment variables using the following commands:

   ```bash
   # Okta OAuth2 Configuration
   export OKTA_OAUTH2_ISSUER="https://dev-74889125.okta.com/oauth2/default"
   export OKTA_OAUTH2_CLIENT_ID="your-okta-client-id"
   export OKTA_OAUTH2_CLIENT_SECRET="your-okta-client-secret"
   export OKTA_OAUTH2_SCOPES="openid,profile,offline_access"

   # Server Port Configuration
   export SERVER_PORT=8080

   # Optional: Database Configuration
   export DATABASE_URL="your-database-url"
   ```

   Replace `your-okta-client-id`, `your-okta-client-secret`, and `your-database-url` with the actual values.

## Running the Backend

### Building and Running

Once the environment variables are configured, you can proceed to build and run the backend.

1. Navigate to the project directory in your Terminal (macOS/Linux) or PowerShell (Windows).
2. Run the following command to build the project:

   ```bash
   mvn clean install
   ```

3. After a successful build, run the application:

   ```bash
   mvn spring-boot:run
   ```

   The application should start and be accessible on `http://localhost:8080`.

## Verifying Installation

To verify that everything is working correctly, follow these steps:

1. **Access the Login Page**: Open a web browser and go to `http://localhost:8080/login`. You should see the login screen that uses Okta for authentication.
2. **Access Protected Routes**: After logging in, attempt to navigate to the protected page (`/protected`). You should only be able to access this route if you are authenticated.
3. **Check API Endpoints**: Verify that the backend API endpoints are functioning as expected by sending test requests using tools like Postman or Curl.

## Troubleshooting

- **CSRF Token Issues**: If you are experiencing CSRF token-related issues, make sure that your frontend application is properly retrieving and sending the CSRF token. Check the network tab in your browser's developer tools to ensure that the token is being included in your requests.
- **Redirect Loop Errors**: A common issue can be infinite redirects when attempting to access protected routes after logging out. This usually happens if there is a misconfiguration in the security setup, particularly in the `SecurityConfiguration` class. Verify that the logout and login pages are configured correctly and that unauthorized users are redirected properly.
- **Okta Configuration**: Ensure that your Okta OAuth2 client ID and secret are correctly configured, and that the redirect URIs set in Okta match what your backend expects.

---

If you encounter any other issues, consult the official Spring Security and Okta documentation for additional guidance.