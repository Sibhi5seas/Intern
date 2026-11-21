# Employee Management REST API

This project is a simple Employee Management REST API built using Java and Spring Boot. It allows users to manage employee details and departments through different API endpoints.

I built this project to practise working with Spring Boot, connecting an application to a MySQL database, and understanding how REST APIs work in a layered architecture.

## What can this project do?

The API supports the following operations:

- Add a new employee.
- View all employees.
- Get employee details using their ID.
- Update employee information.
- Delete an employee.
- View the list of departments.
- Search employees by name.
- Filter employees based on their department.
- Sort and paginate employee records.

It also includes input validation, duplicate email checking and exception handling for invalid requests.

## Technologies Used

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Maven
- Postman

## How the project is organised

I followed a layered structure to keep the code organised and make each part easier to understand and maintain.

```text
Controller
    |
    v
Service
    |
    v
Repository
    |
    v
MySQL Database
```

- **Controller:** Receives API requests and sends responses.
- **Service:** Handles the main application logic.
- **Repository:** Handles database operations.
- **Entity:** Represents the database tables.
- **DTO:** Used to transfer data between the client and application.
- **Exception:** Handles errors that occur while processing requests.

## API Endpoints

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/employees` | Add an employee |
| GET | `/api/employees` | Get all employees |
| GET | `/api/employees/{id}` | Get an employee by ID |
| PUT | `/api/employees/{id}` | Update employee details |
| DELETE | `/api/employees/{id}` | Delete an employee |
| GET | `/api/departments` | Get all departments |

### Search, Filter and Pagination

The employee listing API also supports additional query parameters.

Examples:

```text
/api/employees?name=Priya
/api/employees?departmentId=1
/api/employees?page=0&size=10
/api/employees?sortBy=salary&direction=desc
```

These can also be combined when needed.

## Setting up the project

### 1. Requirements

Make sure you have the following installed:

- Java 21
- MySQL
- Maven
- Postman (for testing the APIs)

### 2. Database setup

Create the database in MySQL:

```sql
CREATE DATABASE employee_management;
```

The SQL script inside the `database` folder contains the department table setup and sample department records.

### 3. Configure database credentials

Update the database username in `application.properties` if required.

Set your MySQL password as an environment variable:

```text
DB_PASSWORD=your_mysql_password
```

The project reads this variable when connecting to MySQL. Make sure you configure it before starting the application.

### 4. Run the application

Open the project folder in the terminal and run:

```bash
mvn spring-boot:run
```

Once the application starts successfully, it will be available at:

```text
http://localhost:8080
```

## Testing with Postman

I have included the exported Postman collection with the project.

Import the collection into Postman to test the different endpoints, including:

- Employee creation and updating
- Retrieving and deleting employees
- Department retrieval
- Search, filtering and pagination
- Validation and error responses

## Error Handling

The project uses `@RestControllerAdvice` for handling exceptions in one place.

Some of the responses handled include:

| Status Code | Situation |
|---|---|
| 400 | Invalid input or validation failure |
| 404 | Employee not found |
| 409 | Duplicate email or database conflict |

## What I learned

While working on this project, I got practical experience with:

- Building REST APIs using Spring Boot.
- Connecting Java applications with MySQL.
- Using Spring Data JPA for database operations.
- Organising code into controller, service and repository layers.
- Working with DTOs and input validation.
- Testing APIs using Postman.
- Handling errors and returning appropriate HTTP status codes.

---