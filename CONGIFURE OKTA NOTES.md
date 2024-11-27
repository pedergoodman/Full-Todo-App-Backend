# Things to change for your repo

Should Be able to change things only in the @ applications.properties [here](./src/main/resources/application.properties)


## You'll need:
your database name
okta.oauth2.issuer
okta.oauth2.client-id
okta.oauth2.client-secret


# Okta App settings
Create app in Okta Admin console
1. OIDC - OpenID Connect
2. Web Application
3. Change Sign-in redirect URIs
- "http://localhost:8080/login/oauth2/code/okta"
4. Add to Sign-out redirect URIs
- "http://localhost:3000" 
5. Controlled access
- allow everyone


Security API
- Access polocies -> new policy all clients
- create rule -> all defaults
