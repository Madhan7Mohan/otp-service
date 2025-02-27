📌 Overview

This is a Spring Boot-based OTP (One-Time Password) Service. It provides REST APIs for generating, storing, sending, and verifying OTPs via messaging services.

📂 Project Structure

madhan7mohan-otp-service/
├── src/main/java/com/example/otpservice/
│   ├── controller/  # Handles API requests
│   ├── dto/         # Request & Response DTOs
│   ├── entity/      # OTP Database Model
│   ├── messaging/   # OTP Messaging Service
│   ├── repository/  # Database Access Layer
│   ├── service/     # OTP Business Logic
│   └── OtpServiceApplication.java  # Main Application Entry
└── src/main/resources/application.properties  # Configuration


## 🚀 API Endpoints
| HTTP Method | Endpoint         | Description |
|-------------|----------------|-------------|
| **POST**    | `/generate`     | Generates and sends an OTP to the user |
| **POST**    | `/verify`       | Verifies if the provided OTP is correct |

### 🔧 Example API Requests

#### **1. Generate OTP**
- **Request:**
```http
POST /generate
```
```json
{
  "phone": "+1234567890"
}
```
- **Response:**
```json
{
  "message": "OTP sent successfully",
  "otp": "123456"
}
```

#### **2. Verify OTP**
- **Request:**
```http
POST /verify
```
```json
{
  "phone": "+1234567890",
  "otp": "123456"
}
```
- **Response:**
```json
{
  "message": "OTP verified successfully"
}
```

## 📌 Setup & Run Instructions

### 1️⃣ Prerequisites
- **Java 17+**
- **Maven**
- **MySQL Database** (or H2 for local testing)

### 2️⃣ Configure Database
Modify the `application.properties` file:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/otp_service
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
```
Create the database manually:
```sql
CREATE DATABASE otp_service;
```

### 3️⃣ Build & Run the Application
```sh
mvn clean install
mvn spring-boot:run
```

## 🔍 Testing with Postman
Use **Postman** or any API testing tool to send requests to the provided endpoints.

## 📜 License
This project is licensed under the **MIT License**, allowing free use, modification, and distribution.
