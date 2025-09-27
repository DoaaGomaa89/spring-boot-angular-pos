# Point of Sale (POS) — Angular + Spring Boot + MySQL

A modern POS web app with an Angular UI, a secure Spring Boot REST API, and a MySQL database. Manage products, categories, customers, suppliers, sales, purchases, and stock levels.

For login: user: admin,  password: admin123

---

# 🚀 Quick Start (Docker — Recommended)

Run the whole stack with Docker. **No local Java/Node/MySQL installs required.**

## Prerequisites
- [Docker](https://docs.docker.com/get-docker/) 20.10+
- [Docker Compose](https://docs.docker.com/compose/install/) v2+
- Git

## 1️⃣ Clone and Start

```bash
git clone https://github.com/DoaaGomaa89/spring-boot-angular-pos.git
cd spring-boot-angular-pos
docker compose up --build
```

> The first run may take a few minutes while images build and dependencies install.

## 2️⃣ Access

- **Frontend (Angular):** http://localhost:4200
- **API / Swagger UI:** http://localhost:8080/swagger-ui.html
- **MySQL:** localhost:3306 (credentials from `.env` / `docker-compose.yml`)

## 🔑 Default Login (Demo)

Use these demo credentials after the app starts:

```
username: admin
password: admin123
```

> For production, change these immediately and rotate any seeded secrets.

## 3️⃣ Stop / Reset

```bash
docker compose down                    # stop
docker compose down -v                 # stop + remove DB volume (clean slate)
docker compose up --build --no-cache   # force a clean rebuild
```

## 4️⃣ MySQL Shell (inside container)

```bash
docker compose exec db mysql -u$MYSQL_USER -p$MYSQL_PASSWORD $MYSQL_DATABASE
```

---

# Tech stack
- **Front-end:** Angular 17 + TypeScript + Angular Router + HttpClient (Material/Bootstrap optional)
- **Back-end:** Spring Boot 3 (Java 17), Spring Security (JWT), JPA/Hibernate
- **Database:** MySQL 8
- **Docs:** OpenAPI/Swagger UI

---

# Dependencies

## Front end
- Angular CLI, Angular Router, HttpClient
- RxJS, TypeScript
- UI kit: Angular Material or Bootstrap (if used)

## Back end
- `spring-boot-starter-web`, `spring-boot-starter-data-jpa`
- `spring-boot-starter-security` (JWT)
- `mysql:mysql-connector-java`
- JJWT (`io.jsonwebtoken:jjwt-*`) for JSON Web Tokens
- Springdoc OpenAPI UI (`springdoc-openapi-starter-webmvc-ui`)
- Lombok (optional)

---

# Configuration

Provide environment variables via a `.env` at the repo root (used by Compose) or export them in your shell.

```bash
# --- MySQL (Docker service name is 'db' by default) ---
SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/pos_db?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC
SPRING_DATASOURCE_USERNAME=app
SPRING_DATASOURCE_PASSWORD=app

# Hibernate
SPRING_JPA_HIBERNATE_DDL_AUTO=update

# JWT (HS512 needs a secret ≥ 64 bytes)
JWT_SECRET=please_generate_a_long_random_secret_string_64chars_or_more

# CORS (dev)
CORS_ORIGINS=http://localhost:4200,http://127.0.0.1:4200
```

> **Windows tip:** `$env:JWT_SECRET="..."` before `docker compose up`, or put it in `.env`.

---

# 🧑‍💻 Manual Setup (Local Dev)

Prefer running without Docker?

## Install prerequisites
- **Java 17** + **Maven**
- **Node.js 18+** + **npm** + **Angular CLI** (`npm i -g @angular/cli`)
- **MySQL 8**

## Clone

```bash
git clone https://github.com/DoaaGomaa89/spring-boot-angular-pos.git
cd spring-boot-angular-pos
```

## Back end (Spring Boot)

```bash
cd backend
mvn -DskipTests package
java -jar target/*.jar
# API http://localhost:8080
# Swagger http://localhost:8080/swagger-ui.html
```
> Dev mode:
```bash
mvn spring-boot:run
```

## Front end (Angular)

```bash
cd frontend
npm ci
ng serve --open    # http://localhost:4200
```

---

# Database schema

- JPA can create/update schema on first run (`SPRING_JPA_HIBERNATE_DDL_AUTO=update`).
- Optional seed data can live in `src/main/resources/data.sql` (e.g., admin user, sample products).

**Manual bootstrap (MySQL):**
```sql
CREATE DATABASE pos_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'app'@'%' IDENTIFIED BY 'YOUR_STRONG_PASSWORD';
GRANT ALL PRIVILEGES ON pos_db.* TO 'app'@'%';
FLUSH PRIVILEGES;
```

---

# Project Structure (key parts)

```
backend/
├── src/main/java/.../pos
│   ├── entity/           # Product, Category, Customer, Supplier, Sale, Purchase, User, ...
│   ├── repository/       # Spring Data JPA
│   ├── controller/       # REST endpoints
│   ├── service/          # Business logic
│   ├── security/         # JWT filters/config
│   └── config/           # CORS, Swagger, etc.
├── src/main/resources/
│   ├── application.yml
│   └── data.sql          # optional seed data (admin, sample data)
└── Dockerfile

frontend/
├── src/
├── angular.json
├── package.json
└── Dockerfile

docker-compose.yml
```

---

# Features

- 🔐 **JWT Authentication** and role-based authorization
- 🧾 **Billing & Sales** (cart, discounts, tax)
- 📦 **Inventory** (products, categories, suppliers, stock movements)
- 👥 **Customers & Suppliers** management
- 📊 **Dashboard** (KPIs, low-stock alerts)
- 📄 **OpenAPI Docs** via Swagger UI

---

# API Documentation

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- JSON spec:  `/v3/api-docs`

---

# Troubleshooting

- **JWT: “key size not secure enough for HS512”**  
  Use a secret **≥ 64 bytes** in `JWT_SECRET`.

- **CORS 403 / “Invalid CORS request”**  
  Include `http://localhost:4200` in `CORS_ORIGINS` and verify backend CORS config.

- **Ports already in use (4200/8080/3306)**  
  Edit mappings in `docker-compose.yml`, e.g.:
  ```yaml
  services:
    frontend:
      ports: ["4201:4200"]
    backend:
      ports: ["8081:8080"]
    db:
      ports: ["3307:3306"]
  ```

- **DB connectivity**  
  ```bash
  docker compose ps
  docker compose logs db
  docker compose exec db mysql -u$MYSQL_USER -p$MYSQL_PASSWORD -e "SHOW TABLES" $MYSQL_DATABASE
  ```

- **Clean rebuild**  
  ```bash
  docker compose down -v
  docker compose up --build --no-cache
  ```
 

