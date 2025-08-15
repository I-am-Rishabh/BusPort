# Bus Reservation System

[![Java](https://img.shields.io/badge/Java-17%2B-orange?style=flat-square&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=flat-square&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.6%2B-red?style=flat-square&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=flat-square&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![JWT](https://img.shields.io/badge/JWT-Auth-000000?style=flat-square&logo=jsonwebtokens&logoColor=white)](https://jwt.io/)
[![Microservices](https://img.shields.io/badge/Architecture-Microservices-blueviolet?style=flat-square&logo=microgenetics&logoColor=white)](https://microservices.io/)
[![REST API](https://img.shields.io/badge/API-REST-02569B?style=flat-square&logo=swagger&logoColor=white)](https://restfulapi.net/)


A comprehensive microservices-based bus reservation system built with Spring Boot, providing user authentication, bus management, route planning, scheduling, and booking capabilities.

## 🏗️ Architecture Overview

The system consists of three main microservices:

- **User Service** - Handles user authentication, registration, and profile management
- **Bus Service** - Manages buses, routes, schedules, and search functionality
- **Booking Service** - Handles ticket bookings, cancellations, and passenger management

## 🛠️ Technology Stack

- **Backend Framework**: Spring Boot 3.x
- **Build Tool**: Maven
- **Database**: MySQL
- **Authentication**: JWT-based authentication
- **Validation**: Jakarta Validation (Bean Validation)
- **Documentation**: Lombok for boilerplate reduction

## 📋 Prerequisites

Before running the application, ensure you have the following installed:

- Java 17 or later
- Maven 3.6 or later
- MySQL 8.0 or later
- Git

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone <repository-url>
cd BusPort
```

### 2. Database Setup

Create three MySQL databases for each microservice:

```sql
CREATE DATABASE user_service_db;
CREATE DATABASE bus_service_db;
CREATE DATABASE booking_service_db;
```

### 3. Configure Application Properties

Update the `application.properties` or `application.yml` files in each service with your MySQL configuration:

#### User Service (`UserService/src/main/resources/application.properties`)
```properties
spring.application.name=user-service
server.port=8081

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/user_service_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# JWT Configuration
jwt.secret=your_jwt_secret_key
jwt.expiration=86400000
```

#### Bus Service (`BusService/src/main/resources/application.properties`)
```properties
spring.application.name=bus-service
server.port=8082

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/bus_service_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

#### Booking Service (`BookingService/src/main/resources/application.properties`)
```properties
spring.application.name=booking-service
server.port=8083

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/booking_service_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### 4. Build and Run Services

Navigate to each service directory and run the following commands:

#### User Service
```bash
cd UserService
mvn clean install
mvn spring-boot:run
```

#### Bus Service
```bash
cd BusService
mvn clean install
mvn spring-boot:run
```

#### Booking Service
```bash
cd BookingService
mvn clean install
mvn spring-boot:run
```

## 📡 API Endpoints

### User Service (Port: 8081)

#### Authentication
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Authenticate user and get JWT token

#### User Profile
- `GET /api/user/profile` - Get user profile (Authenticated)
- `PUT /api/user/profile` - Update user profile (Authenticated)

### Bus Service (Port: 8082)

#### Bus Management
- `POST /api/bus` - Create a new bus
- `PUT /api/bus/{id}` - Update bus information
- `DELETE /api/bus/{id}` - Delete a bus
- `GET /api/bus/{id}` - Get bus by ID
- `GET /api/bus` - Get all buses

#### Route Management
- `POST /api/route` - Create a new route
- `PUT /api/route/{id}` - Update route information
- `DELETE /api/route/{id}` - Delete a route
- `GET /api/route/{id}` - Get route by ID
- `GET /api/route/all` - Get all routes

#### Schedule Management
- `POST /api/schedules` - Create a new schedule
- `PUT /api/schedules/{id}` - Update schedule
- `DELETE /api/schedules/{id}` - Delete schedule
- `PUT /api/schedules/{id}/seats` - Update available seats
- `GET /api/schedules/{id}` - Get schedule by ID
- `GET /api/schedules` - Get all schedules

#### Search
- `GET /api/search?source={source}&destination={destination}&date={date}` - Search buses

### Booking Service (Port: 8083)

#### Booking Management
- `POST /api/bookings` - Create a new booking
- `PUT /api/bookings/{bookingId}/cancel` - Cancel a booking
- `GET /api/bookings/user` - Get user's bookings
- `GET /api/bookings/user/upcoming` - Get upcoming journeys
- `GET /api/bookings/admin/all` - Get all bookings (Admin)
- `GET /api/bookings/check-schedule/{scheduleId}` - Check active bookings for schedule
- `GET /api/bookings/schedule/{scheduleId}/passengers` - Get passengers by schedule

## 🔐 Authentication

The system uses JWT-based authentication. Include the JWT token in the Authorization header for protected endpoints:

```
Authorization: Bearer <your_jwt_token>
```

Some booking endpoints require the user ID to be passed in the `X-User-Id` header.

## 📝 Request/Response Examples

### User Registration
```json
POST /api/auth/register
{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "password": "password123",
    "phoneNumber": "+1234567890"
}
```

### User Login
```json
POST /api/auth/login
{
    "email": "john.doe@example.com",
    "password": "password123"
}
```

### Create Booking
```json
POST /api/bookings
Headers: X-User-Id: 1
{
    "scheduleId": 1,
    "passengers": [
        {
            "name": "John Doe",
            "age": 30,
            "gender": "MALE",
            "seatNumber": "A1"
        }
    ],
    "totalAmount": 500.00
}
```

### Search Buses
```
GET /api/search?source=mumbai&destination=pune&date=2024-12-25
```

## 🏃‍♂️ Running in Development

1. Start MySQL server
2. Create the required databases
3. Update configuration files with your database credentials
4. Run each service in separate terminal windows
5. Services will be available at:
   - User Service: http://localhost:8081
   - Bus Service: http://localhost:8082
   - Booking Service: http://localhost:8083

## 🔧 Project Structure

```
BusPort/
├── UserService/
│   ├── src/main/java/com/reservation/UserService/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── dto/
│   │   └── ...
│   └── pom.xml
├── BusService/
│   ├── src/main/java/com/reservation/BusService/
│   │   ├── Controller/
│   │   ├── Model/
│   │   └── ...
│   └── pom.xml
├── BookingService/
│   ├── src/main/java/com/reservation/BookingService/
│   │   ├── Controller/
│   │   ├── Model/
│   │   └── ...
│   └── pom.xml
└── README.md
```

