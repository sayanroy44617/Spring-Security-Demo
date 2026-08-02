# Spring Security Demo

A simple Spring Boot proof of concept for learning Spring Security with PostgreSQL and JWT authentication.

## What it demonstrates

- User registration and login.
- BCrypt password encoding.
- JWT token generation and validation.
- Protected REST endpoints.

## Tech stack

- Java 17
- Spring Boot 4.1.0
- Spring Security
- Spring Data JPA / Hibernate
- PostgreSQL
- JSON Web Token (JJWT 0.13.0)
- Maven Wrapper

## Prerequisites

- JDK 17 or newer.
- PostgreSQL running locally.
- A database named `spring_security_demo`.

The default datasource configuration expects PostgreSQL at `localhost:5431` with:

```text
Database: spring_security_demo
Username: postgres
Password: password
```

If needed, update `src/main/resources/application.properties` with your PostgreSQL connection details.

## Run the application

From the project root:

```bash
./mvnw spring-boot:run
```

The application starts on the default Spring Boot port, `8080`.

To run the tests:

```bash
./mvnw test
```

To build and run the JAR:

```bash
./mvnw clean package
java -jar target/spring-security-demo-0.0.1-SNAPSHOT.jar
```

## Endpoints

### Register a user

`POST /register` is publicly accessible.

```bash
curl -X POST http://localhost:8080/register \
  -H 'Content-Type: application/json' \
  -d '{"username":"alice","password":"change-me"}'
```

Successful response:

```text
User Registered
```

### Log in

`POST /login` is publicly accessible and returns a JWT when the credentials are valid.

```bash
curl -X POST http://localhost:8080/login \
  -H 'Content-Type: application/json' \
  -d '{"username":"alice","password":"change-me"}'
```

Use the returned token as a Bearer token when calling protected endpoints.

### Call a protected endpoint

```bash
curl http://localhost:8080/security \
  -H "Authorization: Bearer <JWT>"
```

Response:

```text
Security Endpoint
```

### Submit a student

`POST /student` requires authentication.

```bash
curl -X POST http://localhost:8080/student \
  -H 'Content-Type: application/json' \
  -H "Authorization: Bearer <JWT>" \
  -d '{"id":1,"name":"Alice","email":"alice@example.com"}'
```

The endpoint returns the submitted student as its record string representation.

## How it works

1. Register a user through `/register`.
2. Log in through `/login` to receive a JWT.
3. Pass the JWT in the `Authorization` header for protected endpoints.

`/register` and `/login` are public. The other endpoints require authentication.

## Project structure

```text
src/main/java/com/example/springsecuritydemo/
├── config/       Security configuration and JWT filter
├── controller/   Authentication and demo REST endpoints
├── DAO/          Request DTO for username/password payloads
├── model/        JPA user entity, principal, and student record
├── repository/   Spring Data user repository
└── service/      JWT, user lookup, and registration services
```

## Note

This is a learning/demo project. The default configuration is intended for local use.
