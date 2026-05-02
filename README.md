# 📚 Library Management System

<div align="center">

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.6-green?style=flat-square&logo=spring-boot)
![Maven](https://img.shields.io/badge/Maven-3.8+-blue?style=flat-square&logo=apache-maven)
![H2 Database](https://img.shields.io/badge/Database-H2%20In--Memory-lightblue?style=flat-square&logo=database)
![License](https://img.shields.io/badge/License-MIT-brightgreen?style=flat-square)
![Status](https://img.shields.io/badge/Status-Active-success?style=flat-square)

**A modern, feature-rich library management system built with Spring Boot & JSP**

[Features](#-features) • [Tech Stack](#-tech-stack) • [Getting Started](#-getting-started) • [Project Structure](#-project-structure) • [API Endpoints](#-api-endpoints)

</div>

---

## ✨ Features

- 📖 **Book Management** - Add, edit, view, and manage books with author associations
- ✍️ **Author Management** - Create and manage book authors with nationality tracking
- 🔗 **Book-Author Relations** - Establish many-to-one relationships between books and authors
- 💾 **Persistent Storage** - H2 in-memory database with automatic schema generation via Hibernate
- 🎨 **Modern UI** - Responsive JSP frontend with gradient designs and smooth interactions
- 🔍 **Custom Queries** - INNER JOIN queries for comprehensive book-author data retrieval
- ✅ **Input Validation** - Jakarta Bean Validation for all entity inputs
- 🌐 **RESTful Architecture** - Clean separation of concerns with MVC pattern

---

## 🛠️ Tech Stack

<table>
  <tr>
    <td align="center"><strong>Backend</strong></td>
    <td align="center"><strong>Frontend</strong></td>
    <td align="center"><strong>Database</strong></td>
    <td align="center"><strong>Build Tool</strong></td>
  </tr>
  <tr>
    <td>
      • Spring Boot 4.0.6<br/>
      • Spring Web MVC<br/>
      • Spring Data JPA<br/>
      • Hibernate ORM 7.2<br/>
      • Lombok
    </td>
    <td>
      • JSP (Jakarta Server Pages)<br/>
      • JSTL (Tag Libraries)<br/>
      • HTML5<br/>
      • CSS3 (Gradients & Animations)<br/>
      • Responsive Design
    </td>
    <td>
      • H2 Database<br/>
      • HikariCP Connection Pool<br/>
      • DDL Auto Schema Generation
    </td>
    <td>
      • Apache Maven 3.14<br/>
      • Java 17<br/>
      • Spring Boot Maven Plugin
    </td>
  </tr>
</table>

---

## 🚀 Getting Started

### Prerequisites

- **Java 17+** - [Download JDK](https://adoptium.net/)
- **Apache Maven 3.8+** - [Download Maven](https://maven.apache.org/)

### Installation & Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/library-management.git
   cd library-management
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   ```
   Open your browser and navigate to: http://localhost:8080
   ```

### Building for Production

```bash
mvn clean package
java -jar target/library-management-0.0.1-SNAPSHOT.war
```

---

## 📁 Project Structure

```
library-management/
├── src/
│   ├── main/
│   │   ├── java/com/library/library_management/
│   │   │   ├── controller/          # REST Controllers
│   │   │   │   ├── BookController.java
│   │   │   │   ├── AuthorController.java
│   │   │   │   └── HomeController.java
│   │   │   ├── service/             # Business Logic
│   │   │   │   ├── BookService.java
│   │   │   │   └── AuthorService.java
│   │   │   ├── repository/          # Data Access Layer
│   │   │   │   ├── BookRepository.java
│   │   │   │   └── AuthorRepository.java
│   │   │   ├── entity/              # JPA Entities
│   │   │   │   ├── Book.java
│   │   │   │   └── Author.java
│   │   │   ├── dto/                 # Data Transfer Objects
│   │   │   │   └── BookAuthorDTO.java
│   │   │   ├── config/              # Configuration
│   │   │   │   └── DataSeeder.java
│   │   │   └── LibraryManagementApplication.java
│   │   ├── resources/
│   │   │   ├── application.yaml
│   │   │   └── static/
│   │   └── webapp/WEB-INF/views/    # JSP Templates
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
├── pom.xml
└── README.md
```

---

## 🔌 API Endpoints

### 📖 Book Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/books` | Display all books with authors |
| GET | `/books/add` | Show book creation form |
| POST | `/books` | Create a new book |
| GET | `/books/edit/{id}` | Show book edit form |
| POST | `/books/update` | Update an existing book |
| GET | `/books/delete/{id}` | Delete a book |

### ✍️ Author Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/authors` | Display all authors |
| GET | `/authors/add` | Show author creation form |
| POST | `/authors` | Create a new author |
| GET | `/authors/edit/{id}` | Show author edit form |
| POST | `/authors/update` | Update an existing author |
| GET | `/authors/delete/{id}` | Delete an author |

### 🏠 Home

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | Landing page |

---

## 📊 Database Schema

### Authors Table
```sql
CREATE TABLE authors (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    nationality VARCHAR(255),
    email VARCHAR(255)
);
```

### Books Table
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

---

## 🎯 Key Features Explained

### ✅ **INNER JOIN Query**
The `BookRepository` uses a custom JPQL query to fetch books with their author details:
```java
@Query("SELECT new com.library.library_management.dto.BookAuthorDTO(" +
        "b.id, b.title, b.genre, b.publishedYear, b.price, a.name, a.nationality) " +
        "FROM Book b INNER JOIN b.author a")
List<BookAuthorDTO> findAllBooksWithAuthors();
```

### 🔄 **Data Transfer Object Pattern**
`BookAuthorDTO` provides a clean projection of book data without exposing entity internals.

### 💾 **Automatic Data Seeding**
`DataSeeder` runs on application startup to populate sample data for testing.

### 🎨 **Modern UI Components**
- Gradient backgrounds
- Smooth hover effects
- Responsive tables
- Form validation feedback
- Success/Error messages

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
