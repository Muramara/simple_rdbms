# Employee Management System

A full-stack employee management application with a web interface and a custom database REPL tool.

## Project Overview

This project consists of two main components:

1. **Node.js Web Application**: A web-based employee management system with CRUD operations
2. **Java Database REPL**: A command-line database query interface

## Features

### Web Application
- View all employees with their department and role information
- Create new employee records
- Update existing employee information
- Delete employee records
- Clean, responsive UI with modal forms

### Database REPL
- Execute SQL queries directly from the command line
- View query results in a formatted table
- Support for all standard SQL operations (SELECT, INSERT, UPDATE, DELETE, CREATE, DROP)

## Prerequisites

### For Node.js Web Application
- Node.js (v14 or higher)
- PostgreSQL database
- npm or yarn package manager

### For Java REPL
- Java 17 or higher
- Maven 3.x
- PostgreSQL database

## Database Schema

The application uses the following database structure:

- **departments**: Stores department information
- **department_roles**: Defines roles within each department
- **employees**: Contains employee records with references to department roles
- **vw_employees**: A view that joins all tables for easy querying

## Setup Instructions

### Database Setup

1. Create a PostgreSQL database
2. Run the SQL script to create tables and initial data:

```bash
psql -U your_username -d your_database -f Database/pesapal_challenge.sql
```

### Node.js Web Application Setup

1. Install dependencies:

```bash
npm install
```

2. Create a `.env` file in the project root with your database credentials:

```env
PG_USER=your_username
PG_HOST=localhost
PG_DATABASE=your_database
PG_PASSWORD=your_password
PG_PORT=5432
```

3. Start the server:

```bash
node index.js
```

4. Open your browser and navigate to `http://localhost:1304`

### Java REPL Setup

1. Update database credentials in `src/main/java/org/pesapal/Main.java`:

```java
String url = "jdbc:postgresql://localhost:5432/your_database";
String user = "your_username";
String password = "your_password";
```

2. Compile and run using Maven:

```bash
mvn clean compile
mvn exec:java
```

3. Use the REPL by typing SQL commands. Type `EXIT` to quit.

## Project Structure

```
.
├── Database/
│   └── pesapal_challenge.sql       # Database schema and sample data
├── src/main/java/org/pesapal/
│   ├── Main.java                   # Java REPL entry point
│   ├── DbRepl.java                 # REPL implementation
│   ├── DatabaseEngine.java         # Database interface
│   ├── RealDbEngine.java           # PostgreSQL implementation
│   └── QueryResult.java            # Query result wrapper
├── views/
│   └── index.ejs                   # Web UI template
├── index.js                        # Express server and API endpoints
├── package.json                    # Node.js dependencies
└── pom.xml                         # Maven configuration
```

## API Endpoints

### Web Application Routes

- `GET /` - Display all employees
- `POST /add-employee` - Create a new employee
- `POST /update-employee` - Update an existing employee
- `POST /delete-employee` - Delete an employee

## Usage Examples

### Web Application

1. Click "Create Employee" to add a new employee
2. Fill in the employee details and select a department role
3. Click "Edit" to modify employee information
4. Click "Delete" to remove an employee (requires confirmation)

### Java REPL

```sql
mydb=# SELECT * FROM vw_employees;
mydb=# INSERT INTO employees (employee_name, company_email, department_role_id, hire_date) VALUES ('John Doe', 'john.doe@company.com', 1, '2024-01-01');
mydb=# UPDATE employees SET company_email = 'john.d@company.com' WHERE employee_id = 5;
mydb=# DELETE FROM employees WHERE employee_id = 5;
mydb=# EXIT
```

## Technologies Used

### Backend
- Node.js
- Express.js
- PostgreSQL (pg driver)
- EJS templating engine
- body-parser
- dotenv

### Java REPL
- Java 17
- JDBC (PostgreSQL driver)
- Maven

## License

This project is part of the Pesapal technical challenge.

## Notes

- The web application runs on port 1304 by default
- All employee emails are automatically converted to lowercase
- The view `vw_employees` provides a convenient way to query employee data with department and role information
- The Java REPL supports basic PostgreSQL commands and provides formatted output
