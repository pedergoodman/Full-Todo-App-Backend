# Setting Up and Running the Backend

Before you can run the backend application, you need to set up a few environment variables for things like the database, Okta OAuth2, and the server port. These environment variables are required to configure the app for your local development environment.
Step 1: Set Environment Variables

You need to export the following environment variables to ensure the backend connects correctly with Okta, the database, and other settings.
Windows Users (Using PowerShell)

    Open PowerShell on your computer.
    Navigate to the directory where your backend project is located.
    Run the following commands to set the required environment variables:

# Set the Okta OAuth2 settings (Powershell specific I believe)
$env:OKTA_OAUTH2_ISSUER="https://dev-74889125.okta.com/oauth2/default"
$env:OKTA_OAUTH2_CLIENT_ID="your-okta-client-id"
$env:OKTA_OAUTH2_CLIENT_SECRET="your-okta-client-secret"
$env:OKTA_OAUTH2_SCOPES="openid,profile,offline_access"

# Set the server port
$env:SERVER_PORT=8080

Important: Replace your-okta-client-id and your-okta-client-secret with the actual values from your Okta Developer Console.
macOS Users (Using Terminal)

    Open Terminal on your macOS machine.
    Navigate to the directory where your backend project is located.
    Run the following commands to set the required environment variables:

# Set the Okta OAuth2 settings
export OKTA_OAUTH2_ISSUER="https://dev-74889125.okta.com/oauth2/default"
export OKTA_OAUTH2_CLIENT_ID="your-okta-client-id"
export OKTA_OAUTH2_CLIENT_SECRET="your-okta-client-secret"
export OKTA_OAUTH2_SCOPES="openid,profile,offline_access"

# Set the server port
export SERVER_PORT=8080

Important: Replace your-okta-client-id and your-okta-client-secret with the actual values from your Okta Developer Console.
Optional: Additional Environment Variables

You can also set additional environment variables if needed, such as the database URL or other settings required by your app. For example:

# For macOS/Linux
export DATABASE_URL="your-database-url"

# For Windows (PowerShell)
$env:DATABASE_URL="your-database-url"

# Step 2: Run the Backend Application

Once the environment variables are set, you can proceed to build and run the Spring Boot application.
Building and Running the Backend Application

    Open PowerShell (Windows) or Terminal (macOS) and navigate to the directory containing your project.
    Run the following commands to build and start the Spring Boot application:

# For macOS and Windows (in PowerShell)
mvn clean install

# Run the Spring Boot application
mvn spring-boot:run

This will start the backend application, and it should be accessible at http://localhost:8080.
Step 3: Verify the Application is Running

    Open a browser and go to http://localhost:8080.
    You should see the backend application running and should be able to interact with it.

Troubleshooting

    If you encounter any errors related to the environment variables, double-check the values of OKTA_OAUTH2_CLIENT_ID, OKTA_OAUTH2_CLIENT_SECRET, and the server port.
    Ensure that Okta and the database are correctly configured and that you have all necessary permissions to access the resources.
    If the application isn't starting correctly, check the logs for more details and ensure that you have Maven installed and properly configured.

By following these steps, you'll be able to run the backend application locally on both Windows and macOS.