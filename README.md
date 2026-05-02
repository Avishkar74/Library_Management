# Library Management Application

A comprehensive library catalog system built on Spring Boot, enabling organizations to maintain book inventory and author records with relational database integrity.

**Stack:** Java 17 | Spring Boot 4.0.6 | Hibernate ORM | H2 Database | JSP | Maven

---

## Overview

This web-based application provides a structured approach to cataloging books and authors. It allows users to register new titles, associate them with creators, track bibliographic details, and query the combined dataset through an intuitive interface.

### Core Capabilities

- **Catalog Management** — Store and organize book records including title, genre, publication year, and price information
- **Creator Registry** — Maintain author profiles with name, nationality, and contact details
- **Relational Linking** — Define the connection between books and their authors through foreign key relationships
- **Data Retrieval** — Execute complex queries combining book and author information via repository methods
- **Form Processing** — User-friendly web forms for creating and modifying records with error handling
- **Automatic Setup** — Database initialization with sample records upon application startup

---

## Technology Foundation

**Server-Side Framework**
- Spring Boot 4.0.6 as the application runtime environment
- Spring MVC for request routing and response handling
- Spring Data JPA for data persistence abstraction
- Hibernate 7.2 as the object-relational mapping provider
- Lombok for reducing boilerplate code

**Presentation Layer**
- Jakarta Server Pages (JSP) for server-side template rendering
- Jakarta Standard Tag Library (JSTL) for dynamic content generation
- HTML5 markup with CSS3 styling
- Responsive layouts and user interface components

**Data Layer**
- H2 Database for in-memory data storage
- HikariCP for connection pooling and resource management
- Automatic schema initialization and migration

**Build & Compilation**
- Maven 3.14+ for dependency management and build orchestration
- Java 17 runtime and compiler
- Spring Boot Maven Plugin for packaging and execution

---

## Setup & Execution

### System Requirements

- Java Development Kit (JDK) 17 or later
- Maven 3.8 or higher
- A terminal or command-line interface

### Quick Start Guide

1. **Obtain the source code**
   ```bash
   git clone https://github.com/yourusername/library-management.git
   cd library-management
   ```

2. **Compile and package**
   ```bash
   mvn clean install
   ```

3. **Launch the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Open in web browser**
   ```
   http://localhost:8080
   ```

### Production Deployment

For standalone execution:
```bash
mvn clean package
java -jar target/library-management-0.0.1-SNAPSHOT.war
```

The application will initialize with sample data and become ready to serve requests within seconds.

---

## Directory Organization

```
library-management/
├── src/
│   ├── main/
│   │   ├── java/com/library/library_management/
│   │   │   ├── controller/          # Request handlers
│   │   │   │   ├── BookController.java
│   │   │   │   ├── AuthorController.java
│   │   │   │   └── HomeController.java
│   │   │   ├── service/             # Business operations
│   │   │   │   ├── BookService.java
│   │   │   │   └── AuthorService.java
│   │   │   ├── repository/          # Data access methods
│   │   │   │   ├── BookRepository.java
│   │   │   │   └── AuthorRepository.java
│   │   │   ├── entity/              # Domain model classes
│   │   │   │   ├── Book.java
│   │   │   │   └── Author.java
│   │   │   ├── dto/                 # Transfer objects
│   │   │   │   └── BookAuthorDTO.java
│   │   │   ├── config/              # Setup and initialization
│   │   │   │   ├── H2ConsoleConfig.java
│   │   │   │   └── DataSeeder.java
│   │   │   └── LibraryManagementApplication.java
│   │   ├── resources/
│   │   │   └── application.yaml
│   │   └── webapp/WEB-INF/views/    # Page templates
│   │       ├── books/
│   │       │   ├── list.jsp
│   │       │   ├── add.jsp
│   │       │   └── edit.jsp
│   │       └── authors/
│   │           ├── list.jsp
│   │           ├── add.jsp
│   │           └── edit.jsp
│   └── test/
│       └── java/com/library/library_management/
│           ├── repository/
│           └── service/
├── screenshots/
│   └── (application screenshots for documentation)
├── pom.xml
├── submission.md
└── README.md
```

---

## Request Handling Routes

### Book Catalog Endpoints

| HTTP Verb | Path | Action |
|-----------|------|--------|
| GET | `/books` | Retrieve and display all book records |
| GET | `/books/add` | Render form for adding new book |
| POST | `/books` | Submit new book record |
| GET | `/books/edit/{id}` | Render modification form for existing book |
| POST | `/books/update` | Submit updated book record |
| GET | `/books/delete/{id}` | Remove book from database |

### Author Registry Endpoints

| HTTP Verb | Path | Action |
|-----------|------|--------|
| GET | `/authors` | Retrieve and display all author records |
| GET | `/authors/add` | Render form for adding new author |
| POST | `/authors` | Submit new author record |
| GET | `/authors/edit/{id}` | Render modification form for existing author |
| POST | `/authors/update` | Submit updated author record |
| GET | `/authors/delete/{id}` | Remove author from database |

### Navigation

| HTTP Verb | Path | Action |
|-----------|------|--------|
| GET | `/` | Display landing page |

---

## Data Model & Schema

### Authors Table Schema
```sql
CREATE TABLE authors (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    nationality VARCHAR(255),
    email VARCHAR(255)
);
```

### Books Table Schema
```sql
CREATE TABLE books (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    genre VARCHAR(255),
    published_year INT NOT NULL,
    price DOUBLE NOT NULL,
    author_id BIGINT NOT NULL,
    FOREIGN KEY (author_id) REFERENCES authors(id)
);
```

### Relationship Model

The schema implements a one-to-many relationship:
- One author can be associated with multiple books
- Each book must reference exactly one author
- Foreign key constraint ensures referential integrity

---

## Implementation Details

### SQL Join Operations

The `BookRepository` implements a JPQL query that combines book and author data in a single operation:

```java
@Query("SELECT new com.library.library_management.dto.BookAuthorDTO(" +
        "b.id, b.title, b.genre, b.publishedYear, b.price, a.name, a.nationality) " +
        "FROM Book b INNER JOIN b.author a")
List<BookAuthorDTO> findAllBooksWithAuthors();
```

This approach avoids the N+1 query problem and returns a projection containing only necessary fields.

### Data Transfer Objects

The `BookAuthorDTO` class serves as a response object, providing a structured format for combined data without exposing internal entity details to the client layer.

### Bootstrap Process

The `DataSeeder` component executes automatically upon application startup, populating the database with sample records if no data exists, enabling immediate testing and demonstration.

### User Interface

- Form components with server-side validation error handling
- Data tables with sortable columns and action buttons
- Flash messages for user feedback (success/error notifications)
- Responsive design adapting to various screen sizes

---

## 🧪 Testing

Run the test suite:
```bash
mvn test
```

Test coverage includes:
- ✅ Repository tests with Spring Boot Test
- ✅ Service layer tests with mock repositories
- ✅ Entity validation tests

---

## 📝 Configuration

Edit `application.yaml` to customize:

```yaml
spring:
  application:
    name: library-management
  jpa:
    hibernate:
      ddl-auto: create-drop  # Auto-create schema
    show-sql: false
    properties:
      hibernate:
        dialect: org.hibernate.dialect.H2Dialect
  h2:
    console:
      enabled: true
  datasource:
    url: jdbc:h2:mem:librarydb
    driver-class-name: org.h2.Driver

server:
  port: 8080
```

---

## 🚨 Troubleshooting

### Port 8080 Already in Use
```bash
# Kill the process using port 8080
lsof -i :8080 | grep LISTEN | awk '{print $2}' | xargs kill -9
```

### Maven Build Issues
```bash
# Clean Maven cache and rebuild
mvn clean install -U
```

### Database Connection Errors
- Ensure H2 driver is in classpath (included in dependencies)
- Check `application.yaml` for correct JDBC URL

---

## 📈 Performance Optimizations

- ✅ Connection pooling with HikariCP
- ✅ Lazy loading for entity relationships
- ✅ Indexed foreign keys
- ✅ Efficient INNER JOIN queries

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the **MIT License** - see the LICENSE file for details.

---

## 👤 Author

**Library Management Team**
- GitHub: [@yourusername](https://github.com/yourusername)
- Email: your.email@example.com

---

## 🎓 Learning Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA Guide](https://spring.io/projects/spring-data-jpa)
- [Hibernate ORM](https://hibernate.org/)
- [JSP Tutorials](https://www.oracle.com/java/technologies/pageoriented.html)

---

<div align="center">

⭐ If you found this project helpful, please consider giving it a star! ⭐

Made with ❤️ by the development team

</div>
