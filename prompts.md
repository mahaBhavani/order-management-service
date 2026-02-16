## Prompt 1 – Customer Entity

### Prompt Used:
<PASTE EXACT PROMPT HERE>

### AI Generated:
- Customer entity with JPA annotations
- Used Lombok
- Added validation annotations

### Manual Improvements:
- Added @Table(name="customers")
- Added @Column(unique = true, nullable = false) to email
- Added @Column(nullable = false) to name
- Verified jakarta imports

### Learning:
AI generated correct structure but required database-level constraint improvements.



## Prompt 2 – OrderStatus Enum

### Prompt Used:
Generate a simple Java enum named OrderStatus...

### AI Generated:
- Enum with CREATED, PROCESSING, COMPLETED, CANCELLED values.

### Manual Improvements:
- Ensured no extra logic was added.
- Verified clean structure.

### Learning:
AI correctly generated simple enum with minimal instructions.


## Prompt 3 – Order Entity

### Prompt Used:
<PASTE EXACT PROMPT>

### AI Generated:
- Order entity with fields
- Enum mapping
- ManyToOne relationship

### Manual Improvements:
- Added @Table(name="orders")
- Changed fetch type to LAZY
- Added @JoinColumn(nullable = false)
- Ensured EnumType.STRING
- Verified jakarta imports

### Learning:
AI generated correct structure but relationship optimization and DB constraints required manual enhancement.

### Manual Improvements:
- Verified EnumType.STRING
- Ensured ManyToOne fetch is LAZY
- Replaced @Min and @DecimalMin with @Positive (better validation practice)
- Verified precision and scale for BigDecimal


## Prompt 4 – CustomerRepository

### Prompt Used:
Create a Spring Data JPA repository interface named CustomerRepository...

### AI Generated:
- Repository extending JpaRepository<Customer, Long>

### Manual Improvements:
- Ensured no unnecessary annotations
- Kept interface minimal

### Learning:
AI accurately generated repository boilerplate with minimal instructions.


## Prompt 5 – OrderRepository

### Prompt Used:
Create a Spring Data JPA repository interface named OrderRepository...

### AI Generated:
- Repository extending JpaRepository<Order, Long>

### Manual Improvements:
- Verified correct generics
- Ensured minimal structure

### Learning:
AI efficiently generated repository layer with clean instructions.


## Prompt 6 – CustomerRequestDTO

### Prompt Used:
Generate a DTO class named CustomerRequestDTO.

Fields:
- name (String, not blank)
- email (String, valid email)

Requirements:
- Use jakarta.validation annotations
- Use Lombok
- Do not include id field
- Keep it minimal

### AI Generated:
- DTO with name and email
- Validation annotations

### Manual Improvements:
- Ensured no JPA annotations
- Confirmed no id field

### Learning:
AI correctly separated DTO from entity structure.

## Prompt 7 – CustomerResponseDTO

### Prompt Used:
Generate a DTO class named CustomerResponseDTO.

Fields:
- id (Long)
- name (String)
- email (String)

Requirements:
- Use Lombok
- No validation annotations
- No JPA annotations
- Keep it minimal


## Prompt 8 – OrderRequestDTO

### Prompt Used:
Generate a DTO class named OrderRequestDTO.

Fields:
- productName (String, not blank)
- quantity (Integer, must be greater than 0)
- price (BigDecimal, must be greater than 0)
- customerId (Long, not null)

Requirements:
- Use jakarta.validation annotations
- Use Lombok
- Do not include OrderStatus field
- Do not include Customer object
- Keep it minimal


### AI Generated:
- DTO with productName, quantity, price, customerId
- Added validation annotations

### Manual Improvements:
- Ensured no OrderStatus field included
- Confirmed no JPA annotations
- Verified use of @Positive and @NotNull

### Learning:
AI correctly generated request structure.
Customer reference handled via customerId for clean API design.


## Prompt 9 – OrderResponseDTO

### Prompt Used:
Generate a DTO class named OrderResponseDTO.

Fields:
- id (Long)
- productName (String)
- quantity (Integer)
- price (BigDecimal)
- status (OrderStatus)
- customerId (Long)

Requirements:
- Use Lombok
- No validation annotations
- No JPA annotations
- Keep it minimal

---

### AI Generated:
- DTO class with id, productName, quantity, price, status, and customerId
- Used Lombok annotations for boilerplate reduction
- Clean structure without persistence annotations

---

### Manual Improvements:
- Verified no validation annotations were added
- Confirmed no JPA annotations were included
- Ensured OrderStatus type was correctly referenced
- Confirmed no Customer object was exposed

---

### Learning:
AI effectively generated a clean response DTO.
Maintained separation be

## Prompt 10 – CustomerService

### Prompt Used:
Generate a Spring service class named CustomerService...

### AI Generated:
- Service annotated with @Service
- Constructor injection
- Methods for create, get by id, and get all
- DTO to entity mapping included

### Manual Improvements:
- Replaced Optional.get() with orElseThrow()
- Ensured no entity exposure to controller
- Cleaned mapping logic

### Learning:
AI generated service boilerplate effectively.
Manual refinement required for proper exception handling.


## Prompt 11 – ResourceNotFoundException

### Prompt Used:
Generate a custom exception class named ResourceNotFoundException...

### AI Generated:
- Exception extending RuntimeException
- Constructor with message

### Manual Improvements:
- Verified correct package structure
- Imported into CustomerService

### Learning:
Custom exceptions allow proper REST error handling instead of generic RuntimeException.


## Prompt 12 – GlobalExceptionHandler

### Prompt Used:
Generate a GlobalExceptionHandler class...

### AI Generated:
- ControllerAdvice class
- 404 handler for ResourceNotFoundException
- 400 handler for validation errors
- Structured JSON error response

### Manual Improvements:
- Improved validation error formatting
- Verified correct HTTP status codes
- Ensured clean JSON response structure

### Learning:
AI generated structured exception handling.
Manual refinement ensured proper REST error standards.

## Prompt 13 – OrderService

### Prompt Used:
Generate a Spring service class named OrderService...

### AI Generated:
- Service with constructor injection
- Methods for create, get, list, update status, delete
- DTO to entity mapping included

### Manual Improvements:
- Ensured default status set to CREATED
- Verified customer existence validation
- Replaced Optional.get() with orElseThrow()
- Confirmed only status field updated
- Ensured response DTO does not expose Customer entity

### Learning:
AI generated structured business logic.
Manual validation ensured proper REST and domain consistency.

## Prompt 14 – Add Logging to OrderService

### Prompt Used:
Add SLF4J logging to OrderService for order creation and status updates.

### AI Generated:
- Logger instance
- Info logs in create and update methods

### Manual Improvements:
- Verified log format
- Ensured sensitive data not logged

### Learning:
Logging is essential for production monitoring and debugging.

## Prompt 15 – CustomerController

### Prompt Used:
Generate a REST controller class named CustomerController...

### AI Generated:
- REST controller with POST and GET endpoints
- Proper mappings
- DTO usage

### Manual Improvements:
- Ensured HTTP 201 returned for POST
- Verified use of @Valid
- Confirmed no business logic inside controller

### Learning:
AI generated clean REST layer.
Manual review ensured correct HTTP semantics.


## Prompt 16 – OrderController

### Prompt Used:
Generate a REST controller class named OrderController...

### AI Generated:
- REST controller with POST, GET, PUT, DELETE endpoints
- Proper DTO usage
- Constructor injection

### Manual Improvements:
- Ensured HTTP 201 for POST
- Ensured HTTP 204 for DELETE
- Verified no business logic included
- Confirmed clean enum handling for status update

### Learning:
AI effectively generated REST layer boilerplate.
Manual validation ensured correct HTTP semantics and clean API design.

## Prompt 17 – UpdateOrderStatusDTO

### Prompt Used:
Generate a DTO class named UpdateOrderStatusDTO...

### AI Generated:
- DTO with OrderStatus field
- Used @NotNull validation
- Used Lombok

### Manual Improvements:
- Verified no JPA annotations
- Integrated into OrderController
- Added @Valid to controller method

### Learning:
Using a dedicated DTO improves API design and future extensibility.
Avoids exposing raw enum in request body.


### Manual Improvements:
- Added @NotNull validation on status
- Replaced manual getters/setters with Lombok
- Ensured consistency with other DTO classes

## Prompt 19 – CustomerService Test

### Prompt Used:
Generate a JUnit 5 test class for CustomerService...

### AI Generated:
- Mockito setup
- Basic test methods

### Manual Improvements:
- Refined assertions
- Added negative test case
- Verified exception handling

### Learning:
AI accelerated test skeleton creation while manual review ensured correctness.
