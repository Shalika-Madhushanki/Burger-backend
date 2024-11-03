
```markdown
# Burger API

A RESTful API for a burger restaurant built with Spring Boot and PostgreSQL. This API allows the following operations

## Features
- CRUD operations for Burger and Drink entities
- Search and filter functionality
- PostgreSQL database integration
- Docker Compose support for easy setup and deployment

## Prerequisites

Before you begin, ensure you have the following installed on your machine:
- [Java 17](https://adoptopenjdk.net/)
- [Gradle](https://gradle.org/install/)
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Getting Started

### 1. Download the zip file and extract it

```bash
cd burger-api
```

### 2. Build the Application with Gradle

Use the following command to build the application. This will generate a JAR file in the `build/libs` directory.

```bash
./gradlew build
```

### 3. Run the Application with Docker Compose

Docker Compose will start both the PostgreSQL database and the Spring Boot application.

```bash
docker-compose up --build
```

This command will:
- Download the necessary Docker images
- Start a PostgreSQL container on port `5432`
- Build the Spring Boot application and start it on port `8080`

### 4. Access the API

Once Docker Compose has started the services, you can access the API at:

- Base URL: `http://localhost:8080`
- Swagger UI (for API documentation): `http://localhost:8080/swagger-ui/` (if Swagger is enabled)

## API Endpoints

Here are some of the main API endpoints:

### Burger Endpoints

| Method | Endpoint                  | Description                      |
|--------|----------------------------|----------------------------------|
| GET    | `/api/v1/burgers`             | Get all burgers                  |
| GET    | `/api/v1/burgers/{id}`        | Get a specific burger by ID      |
| POST   | `/api/v1/burgers`             | Create a new burger              |
| PUT    | `/api/v1/burgers/{id}`        | Update an existing burger        |
| DELETE | `/api/v1/burgers/{id}`        | Delete a burger                  |
| GET    | `/api/v1/burgers/search`      | Search burgers by name           |
| GET    | `/api/v1/burgers/filter`      | Filter burgers by price range    |

### Drink Endpoints

| Method | Endpoint                  | Description                      |
|--------|----------------------------|----------------------------------|
| GET    | `/api/v1/drinks`              | Get all drinks                   |
| GET    | `/api/v1/drinks/{id}`         | Get a specific drink by ID       |
| POST   | `/api/v1/drinks`              | Create a new drink               |
| PUT    | `/api/v1/drinks/{id}`         | Update an existing drink         |
| DELETE | `/api/v1/drinks/{id}`         | Delete a drink                   |
| GET    | `/api/v1/drinks/search`       | Search drinks by name            |
| GET    | `/api/v1/drinks/filter`       | Filter drinks by price range     |

## Sample Requests

### 1. Create a Burger
Use the following JSON body with a `POST` request to `/api/v1/burgers`:

```json
{
  "name": "Big Burger",
  "price": 9.99,
  "weight": 180,
  "isAvailable": true,
  "imageUrl": "https://example.com/images/big-burger.jpg",
  "isVegetarian": false,
  "description": "A delicious burger with double cheese and crispy bacon.",
  "ingredients": ["Beef Patty", "Cheese", "Lettuce", "Tomato", "Pickles", "Bacon"],
  "allergens": ["Gluten", "Dairy"]
}

```

### 2. Search for a Burger by Name
Send a `GET` request to search for burgers by name:

```plaintext
http://localhost:8080/api/v1/burgers/search?name=Big
```

### 3. Filter Burgers by Price Range
Send a `GET` request to filter burgers by price range:

```plaintext
http://localhost:8080/api/v1/burgers/filter?minPrice=5&maxPrice=15
```

## Stopping the Application

To stop the application, use the following command:

```bash
docker-compose down
```

This command will stop and remove the containers, networks, and volumes created by Docker Compose.

