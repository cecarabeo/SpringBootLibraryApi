# Spring Boot Library API

A RESTful API built with Spring Boot for managing a personal book library. The application integrates with Google Books API to fetch book information by ISBN and provides full CRUD operations for book management.

## 🚀 Features

- **Google Books Integration**: Automatically fetch book details using ISBN
- **CRUD Operations**: Full Create, Read, Update, Delete functionality for books
- **PostgreSQL Database**: Persistent data storage
- **RESTful API**: Clean REST endpoints for all operations
- **Data Validation**: Input validation using Spring Boot Validation
- **Lombok Integration**: Reduced boilerplate code with annotations

## 🛠 Technology Stack

- **Java 17**
- **Spring Boot 3.5.3**
- **Spring Data JPA** - Database abstraction layer
- **Spring WebFlux** - Reactive web client for Google Books API
- **PostgreSQL** - Primary database
- **Lombok** - Code generation library
- **Maven** - Build tool

## 📋 Prerequisites

Before running the application, ensure you have:

- Java 17 or higher installed
- Maven 3.6+ installed
- PostgreSQL database running
- Git (for cloning the repository)

## 🗄 Database Setup

1. Install and start PostgreSQL
2. Create a database named `bookdb`:
   ```sql
   CREATE DATABASE bookdb;
   ```
3. Update database credentials in `src/main/resources/application.properties` if needed:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/bookdb
   spring.datasource.username=postgres
   spring.datasource.password=pass
   ```

## 🚀 Getting Started

### Clone the Repository

```bash
git clone <repository-url>
cd SpringBootLibraryApi
```

### Run the Application

```bash
# Using Maven wrapper (recommended)
./mvnw spring-boot:run

# Or using Maven directly
mvn spring-boot:run
```

The application will start on `http://localhost:9090`

## 📚 API Endpoints

### Book Management

| Method   | Endpoint          | Description          | Request Body |
| -------- | ----------------- | -------------------- | ------------ |
| `GET`    | `/api/books`      | Get all books        | None         |
| `GET`    | `/api/books/{id}` | Get book by ID       | None         |
| `POST`   | `/api/books`      | Create a new book    | Book JSON    |
| `PUT`    | `/api/books/{id}` | Update existing book | Book JSON    |
| `DELETE` | `/api/books/{id}` | Delete a book        | None         |

### Google Books Integration

| Method | Endpoint                              | Description                        | Parameters           |
| ------ | ------------------------------------- | ---------------------------------- | -------------------- |
| `GET`  | `/api/books/find-by-isbn?isbn={isbn}` | Find book by ISBN (without saving) | `isbn` (query param) |
| `POST` | `/api/books/add-by-isbn?isbn={isbn}`  | Add book to library by ISBN        | `isbn` (query param) |

## 📝 Book Model

```json
{
  "id": 1,
  "title": "The Great Gatsby",
  "isbn10": "0743273567",
  "isbn13": "9780743273565",
  "authors": ["F. Scott Fitzgerald"],
  "publisher": "Scribner",
  "publishDate": "2004-09-30",
  "description": "The story of the mysteriously wealthy Jay Gatsby...",
  "categories": ["Fiction", "Classic Literature"],
  "cover": "http://example.com/cover.jpg"
}
```

## 🔧 Example API Usage

### Add a book by ISBN

```bash
curl -X POST "http://localhost:9090/api/books/add-by-isbn?isbn=9780743273565"
```

### Get all books

```bash
curl -X GET "http://localhost:9090/api/books"
```

### Add a book manually

```bash
curl -X POST "http://localhost:9090/api/books" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Sample Book",
    "authors": ["John Doe"],
    "isbn13": "9781234567890",
    "publisher": "Sample Publisher"
  }'
```

### Update a book

```bash
curl -X PUT "http://localhost:9090/api/books/1" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Updated Book Title",
    "authors": ["Jane Doe"],
    "isbn13": "9781234567890"
  }'
```

### Delete a book

```bash
curl -X DELETE "http://localhost:9090/api/books/1"
```

## 🏗 Project Structure

```
src/
├── main/
│   ├── java/com/example/springbootlibraryapi/
│   │   ├── SpringBootLibraryApiApplication.java    # Main application class
│   │   ├── config/
│   │   │   └── WebClientConfig.java               # WebClient configuration
│   │   ├── controller/
│   │   │   └── BookController.java                # REST endpoints
│   │   ├── model/
│   │   │   ├── Book.java                          # Book entity
│   │   │   └── GoogleBooks/                       # Google Books API models
│   │   ├── repository/
│   │   │   └── BookRepository.java                # Data access layer
│   │   └── service/
│   │       ├── BookService.java                   # Business logic interface
│   │       ├── GoogleBooksService.java            # Google Books integration
│   │       └── impl/                              # Service implementations
│   └── resources/
│       └── application.properties                 # Application configuration
└── test/                                          # Test classes
```

## 🐛 Troubleshooting

### Common Issues

**Database Connection Error**

- Ensure PostgreSQL is running
- Verify database credentials in `application.properties`
- Check if the `bookdb` database exists

**Port Already in Use**

- Change the port in `application.properties`: `server.port=8080`
- Or kill the process using port 9090

**Google Books API Issues**

- Ensure you have an internet connection
- The Google Books API has rate limits; avoid rapid successive calls

## 🐳 Docker Compose

Run the application and a local PostgreSQL instance with Docker Compose.

Build and start the services:

```bash
docker-compose up --build
```

This will expose the API at `http://localhost:9090` and a Postgres database at `localhost:5432`.

Environment variables for the app service are configured in `docker-compose.yml` to point the application at the `db` service:

- `SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/bookdb`
- `SPRING_DATASOURCE_USERNAME=postgres`
- `SPRING_DATASOURCE_PASSWORD=pass`

To run the services in the background:

```bash
docker-compose up --build -d
```

To stop and remove containers, networks and volumes created by `up`:

```bash
docker-compose down -v
```

Notes:

- The `Dockerfile` in the project root performs a multi-stage build (Maven build stage, lightweight runtime image).
- If you want to run the app against a local Postgres instance instead of the container, update `src/main/resources/application.properties` accordingly.
