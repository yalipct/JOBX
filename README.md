# JOBarcelona-22-Back-end

>  Qualifying backend challenge that is part of the JOBarcelona '22 online hackathon.
>  The challenge is to implement an authentication system (Auth) using a Json Web Token.

## Background | Problem to solve
The JOBX team needs to implement an authentication system (Auth) using Json Web Token to continue securely.
In order to finish the implementation, it is necessary to decrypt a document that contains key information such as the secret libraries used to document and design the component.


## Documentation

Annotation based documentation for REST APIs implemented with Spring Web/Rest.

Once the application is running, there is a browser access for

SWAGGER UI http://localhost:3030/swagger-ui.html

OpenAPI File http://localhost:3030/v3/api-docs

## Use of the application

There are two controllers:

###### AuthController

Two methods one to register and one to log users. 

Access: http://localhost:3030/auth
```
POST("/new") to signup (RequestBody username/email/password/role[by default is user])

POST("/login") to login and generate a token if credentials are correct (RequestBody username/password)
```
* User with admin rights
```
{
 username:"jobx_admin"
 email:"admin@jobx.com",
 password:"aJOBX90_min25k."
}
```

###### UserListController 

Additional endpoint to get a database list of all users that can only be accessed by the previous admin user.

Access: http://localhost:3030/user-list
```
Returns the list of all users.
```

## Status Codes

| Status Code | 200 | 400 | 401 |
| :---: | :---: | :---: | :---: |
| Description | `OK` | `BAD REQUEST` | `UNAUTHORIZED` |

## Code Quality
Checked in: https://sonarcloud.io/
