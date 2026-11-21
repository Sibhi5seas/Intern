# Student CRUD using Spring Boot

This is a small beginner-level project. It has two ways to store student details:

1. `/memory/students` uses an ArrayList. The data is lost when the application stops.
2. `/students` uses Spring Data JPA and MySQL. The data stays in the database.

## Requirements

- Java 17 or newer
- Maven
- MySQL Server
- Postman (to test the APIs)

## Run the project

1. Open this folder in VS Code or IntelliJ.
2. Open `src/main/resources/application.properties`.
3. Change `YOUR_MYSQL_PASSWORD` to your MySQL root password.
4. Make sure MySQL Server is running.
5. Open a terminal in the project folder and run:

   `mvn spring-boot:run`

6. Open `http://localhost:8080` (the APIs can be tested using Postman).

Spring Boot will create the `student_db` database if it does not exist. Hibernate will create/update the student table.

## API details

### In-memory CRUD

| Method | URL | Work |
|---|---|---|
| GET | `/memory/students` | Get all students |
| GET | `/memory/students/1` | Get student with id 1 |
| POST | `/memory/students` | Add a student |
| PUT | `/memory/students/1` | Update student with id 1 |
| DELETE | `/memory/students/1` | Delete student with id 1 |

### MySQL CRUD

| Method | URL | Work |
|---|---|---|
| GET | `/students` | Get all students |
| GET | `/students?name=arun` | Search names containing arun |
| GET | `/students/1` | Get student with id 1 |
| POST | `/students` | Add a student |
| PUT | `/students/1` | Update student with id 1 |
| DELETE | `/students/1` | Delete student with id 1 |

## Example JSON

Use this body for POST and PUT requests:

```json
{
  "name": "Arun",
  "department": "CSE",
  "age": 20
}
```

## HTTP status codes used

- `200 OK` - GET and successful update
- `201 Created` - student added
- `204 No Content` - student deleted
- `404 Not Found` - the given student id does not exist

## Main Spring Boot concepts used

- `@RestController` - makes the class handle REST requests.
- `@RequestMapping` - sets the common URL for the controller.
- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping` - map HTTP methods to Java methods.
- `@PathVariable` - gets the id from the URL.
- `@RequestParam` - gets a value from the query string.
- `@RequestBody` - reads student details from JSON.
- `@Entity` - marks Student as a database table.
- `@Id` - marks the primary key.
- `@GeneratedValue` - lets the database generate the id.
- `JpaRepository` - provides methods like `findAll`, `findById`, `save`, `existsById` and `deleteById`.

## Note

The in-memory API and MySQL API are separate examples. Use the `/memory/students` URLs for the ArrayList part and `/students` URLs for the MySQL/JPA part. The in-memory list starts empty each time the application restarts.
