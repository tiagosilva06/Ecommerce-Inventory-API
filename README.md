# 📦 Ecommerce Inventory API

A REST API built with **Java and Spring Boot** for complete inventory and order management in a real e-commerce scenario.

The system integrates products, inventory movements, and orders with automatic quantity control, JWT authentication, and Docker containerization.

---

## 📋 Table of Contents

- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Security](#security)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Environment Variables](#environment-variables)
    - [Running with Docker](#running-with-docker)
- [API Documentation](#api-documentation)
    - [Authentication](#authentication)
    - [Products](#products)
    - [Inventory](#inventory)
    - [Orders](#orders)
- [Business Rules](#business-rules)
- [Tests](#tests)
- [Database Migrations](#database-migrations)
- [Possible Improvements](#possible-improvements)
- [Project Goals](#project-goals)
- [Author](#author)

---

## Tech Stack

| Technology | Version | Purpose |
|---|---|---|
| Java | 17 | Main language |
| Spring Boot | 3.x | Base framework |
| Spring Security | 6.x | Authentication and authorization |
| Spring Data JPA | 3.x | Data persistence |
| MySQL | 8 | Relational database |
| Flyway | - | Database migrations |
| Docker & Docker Compose | - | Containerization |
| JWT (auth0) | - | Authentication tokens |
| BCrypt | - | Password hashing |
| JUnit + Mockito | - | Unit testing |

---

## Architecture

The project follows a layered architecture with clear separation of concerns:

```
src/
├── controller/       → HTTP entry point, request validation
├── service/          → Business rules
├── repository/       → Data access (Spring Data JPA)
├── entity/           → Domain modeling
├── dto/              → Data Transfer Objects
├── config/
│   └── security/     → Spring Security config + JWT filter
└── migrations/       → Flyway scripts
```

---

## Security

- Authentication via **JWT (Bearer Token)**
- Passwords encrypted with **BCrypt**
- Custom `SecurityFilter` that intercepts and validates the token on every request
- **Stateless** session — no server-side state
- `/login` endpoint is public — all other endpoints are protected

---

## Getting Started

### Prerequisites

- [Docker](https://www.docker.com/) and Docker Compose installed
- Java 17+ *(only required to run without Docker)*

### Environment Variables

Create a `.env` file in the project root (or configure directly in `docker-compose.yml`):

```env
SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/ecommerce_db
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=root
JWT_SECRET=your_secret_key_here
```

### Running with Docker

```bash
# Start all containers
docker compose up --build

# Run in background
docker compose up --build -d

# Stop containers
docker compose down
```

After startup:
- **API:** `http://localhost:8080`
- **MySQL:** port `3307`

---

## API Documentation

### Authentication

#### 1. Login

```http
POST /login
Content-Type: application/json
```

```json
{
  "login": "user@ecommerce.com",
  "password": "yourpassword"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

#### 2. Using the token

```http
Authorization: Bearer YOUR_TOKEN_HERE
```

---

### Products

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| `POST` | `/products` | Create product | ✅ Yes |
| `GET` | `/products` | List products (paginated) | ✅ Yes |
| `GET` | `/products?name=xx` | Search product by name | ✅ Yes |
| `PUT` | `/products/{id}` | Update product | ✅ Yes |
| `DELETE` | `/products/{id}` | Soft delete product | ✅ Yes |

**Create product example:**
```json
{
  "name": "Dell Notebook",
  "description": "Notebook i7 16GB RAM",
  "price": 4500.00,
  "quantity": 10
}
```

---

### Inventory

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| `POST` | `/movement/entry` | Stock entry | ✅ Yes |
| `POST` | `/movement/exit` | Stock exit | ✅ Yes |

**Stock entry example:**
```json
{
  "productId": 1,
  "quantity": 50
}
```

---

### Orders

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| `POST` | `/orders` | Create order | ✅ Yes |
| `GET` | `/orders` | List orders | ✅ Yes |
| `GET` | `/orders/{id}` | Get order by ID | ✅ Yes |

**Create order example:**
```json
{
  "customerName": "Tiago Silva",
  "items": [
    {
      "productId": 1,
      "quantity": 2
    },
    {
      "productId": 3,
      "quantity": 1
    }
  ]
}
```

**Response:**
```json
{
  "id": 1,
  "customerName": "Tiago Silva",
  "totalValue": 9500.00,
  "items": [...]
}
```

---

## Business Rules

- ❌ Negative stock is not allowed
- ❌ Orders are rejected if there is insufficient stock
- ✅ Automatic stock deduction after order creation
- ✅ Automatic total order value calculation
- ✅ Soft delete on products — records are not removed from the database
- ✅ Input validation with Bean Validation (`@Valid`)

---

## Tests

Unit tests implemented with **JUnit + Mockito** for the service layer:

```bash
# Run tests
./mvnw test
```

Coverage:
- `ProductService`
- `InventoryMovementService`
- `OrderService`

---

## Database Migrations

The database is versioned with Flyway. Migrations run automatically on application startup, creating all necessary tables.

---

## Possible Improvements

- [ ] Role-based access control (ADMIN / USER)
- [ ] Pagination for orders
- [ ] Structured logging (e.g. Logback + ELK)
- [ ] Swagger / OpenAPI interactive documentation
- [ ] Cloud deployment (AWS / Render / Railway)
- [ ] Integration tests
- [ ] Rate limiting

---

## Project Goals

This project demonstrates proficiency in:

- Backend development with **Java**
- Building **REST APIs**
- **Security** with JWT and Spring Security
- **Domain modeling** and JPA relationships
- Best practices: **Clean Code, SOLID, layered architecture**
- **Containerization** with Docker
- **Database versioning** with Flyway
- **Unit testing** with JUnit and Mockito

---

## Author

**Tiago Silva**
[GitHub](https://github.com/tiagosilva06) • [LinkedIn](https://linkedin.com/in/tiago-silvadev)