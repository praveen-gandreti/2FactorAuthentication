## 2 FACTOR-AUTHENTICATION

#### FLOW 
    -> To access the private END-POINTS like "/admin" we need valid JWT token, To get the token we need call "/login" with USERNAME & PASSWORD in request body,the "/login" will check the credentials and generate one OTP and send it to EMAIL,
    -> To get the JWT token we need to call "/generate" with USERNAME & OTP which is received into your Email, the "/generate" method will validate the USERNAME and OTP if both are valid then it will create a new JWT token and send it as RESPONSE.
    -> To call " /admin " we need to insert the JWT token in the Hearders to call the private END-POINT, once the JWT token is valid it will allow you to aceess the END-POINT

###### In This i have used H2 In-Memory Database
###### Token expired time 2 min

#### REST-API ENDPOINTS :

##### /USER/SAVE (public)
  ###### This API is used to insert user into H2 Database.
##### /login (public)
  ###### This API is used to Validate the USER credentials (USERNAME , PASSWORD)
##### /generate (public)
  ###### This API is used to generate OTP 
##### /demo (public)
  ###### This is a public everyone can access without authentication
##### /admin (Private)
  ###### This API is now accessable to outside world we need valid JWT token to access this endpoint. 


