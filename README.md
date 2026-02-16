Order Management Service

A Spring Boot REST microservice for managing Customers and their Orders.

This project demonstrates:

REST API design

CRUD operations

Spring Data JPA integration

DTO-based API architecture

Validation and global exception handling

Logging of business events

AI-assisted development using Cursor

Tech Stack

Java 17

Spring Boot 3.x

Spring Web

Spring Data JPA

H2 In-Memory Database

Maven

JUnit 5 & Mockito

Cursor AI

Project Structure

controller
service
repository
entity
dto
exception
config (optional)
src/test

Architecture Flow:

Controller → Service → Repository → Database

DTOs are used to avoid exposing JPA entities directly.

How to Run the Application

Clone the repository:

git clone https://github.com/mahaBhavani/order-management-service.git

cd order-management-service

Build the project:

mvn clean install

Run the application:

mvn spring-boot:run

Application runs on:

http://localhost:8080

H2 Database Console

URL:

http://localhost:8080/h2-console

JDBC URL:

jdbc:h2:mem:testdb

Username:

sa

Password:

(empty)

API Endpoints
Customer APIs

Create Customer
POST /customers

Example:

curl -X POST http://localhost:8080/customers
 -H "Content-Type: application/json" -d '{"name":"John Doe","email":"john@example.com
"}'

Get Customer by ID
GET /customers/{id}

List All Customers
GET /customers

Order APIs

Create Order
POST /orders

Example:

curl -X POST http://localhost:8080/orders
 -H "Content-Type: application/json" -d '{"productName":"Laptop","quantity":2,"price":50000,"customerId":1}'

Get Order by ID
GET /orders/{id}

List All Orders
GET /orders

Update Order Status
PUT /orders/{id}/status

Example:

curl -X PUT http://localhost:8080/orders/1/status
 -H "Content-Type: application/json" -d '{"status":"COMPLETED"}'

Delete Order
DELETE /orders/{id}

Validation & Error Handling

Email must be valid

Quantity must be greater than 0

Price must be greater than 0

404 returned when resource not found

400 returned for validation errors

Example error response:

{
"timestamp": "...",
"status": 400,
"error": "Bad Request",
"message": "email: must be a well-formed email address"
}

Logging

The system logs:

Order creation

Order status updates

Using SLF4J.

Running Tests

Run:

mvn test

Includes:

CustomerService unit tests

OrderService unit tests

Business logic validation tests

AI Usage (Cursor)

Cursor AI was used for:

Generating entity classes

Repository interfaces

DTO classes

Controller boilerplate

Exception handling

Unit test scaffolding

All prompts and manual refinements are documented in:

cursor-prompts.md

AI-generated code was reviewed and improved manually to ensure clean architecture and best practices.

Submission Includes

GitHub repository

README.md

cursor-prompts.md

Unit tests

Clean commit history