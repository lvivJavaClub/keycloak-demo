[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

# demo-keycloak-spring-boot-rest-app
Demo REST app with Keycloak & Spring Boot 2.7.0 & Java 17

## Build

    mvn clean package -DskipTests

## Run

    docker-compose up -d

    mvn spring-boot:run

## Stop

    docker-compose down -v

## Examples

Security with Keycloak

UI

    http://localhost:5555/auth/

    user: admin password: admin

    Add realm > Import: Select file src/main/resources/keycloak_demo_realm.jsom > Create

Get access token for two users: admin & user

```shell
#admin: admin@localhost | 1234
export TOKEN=$(curl -X POST 'http://localhost:5555/auth/realms/keycloak_demo/protocol/openid-connect/token' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  --data "client_id=backend&username=admin@localhost&password=1234&client_secret=exZ0FJUf2ncvzPdctyg4r6KDi96YJHM6&grant_type=password" |
  jq -r '.access_token')
echo $TOKEN 
``` 

```shell
#user:  user1@localhost | 1234
export TOKEN=$(curl -X POST 'http://localhost:5555/auth/realms/keycloak_demo/protocol/openid-connect/token' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  --data "client_id=backend&username=user1@localhost&password=1234&client_secret=exZ0FJUf2ncvzPdctyg4r6KDi96YJHM6&grant_type=password" |
  jq -r '.access_token')
```

Decode the access_token using https://jwt.io.
```shell
echo $TOKEN
```

Authorized rest requests
```shell
curl 'http://localhost:8088/api/users' \
-H "Authorization: Bearer $TOKEN" | jq
```
```shell
curl 'http://localhost:8088/api/users' \
-H "Authorization: Bearer $TOKEN" | jq
```
```shell
curl -X POST 'http://localhost:8088/api/users' \
-H "Content-type: application/json" \
-H "Authorization: Bearer $TOKEN" \
--data '{"username":"tester122","firstName":"Test22","lastName":"LastTest22","email":"tester22@localhost","attributes":{"lang":["en"]}}'| jq
```


## Keycloak
https://www.keycloak.org/documentation
