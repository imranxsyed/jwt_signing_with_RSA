# JSON Web Token with Java Example

## Run the tests
1) Go to JWTDemoTest.java
2) Right click and run
3) JWT token, Header, and Claims should be printed in console.
4) Filter the console by typing in searchbar the following text
    Header = //to filter the header
    jwt = //to see the token
    claims = //to see the claims



!!!NOTE!!!
 The RSA private key is hard coded in the variable SECRET_KEY in JWTDemo.java. if you wish to use this token, update the public key the portal.
 Currently the token is available from today 09/20/21 till 09/20/22. If you are using this project after 09/20/22. Update the issue time and expiration time of the
 token in JWTDemo.createJWTSignedToken()

Public and Private keys are provided uner RSA directory of this project.



