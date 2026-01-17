# Employee Management System

A full-stack employee management application with a web interface and a custom database REPL tool.

## Project Overview

This project implements a complete relational database management system (RDBMS) with two main components:

1. **Java Database REPL**: A custom SQL query interface providing an interactive command-line environment for database operations
2. **Node.js Web Application**: A web-based employee management system demonstrating CRUD operations with the database

The project showcases a functional RDBMS implementation with support for table declarations, CRUD operations, indexing, primary and unique key constraints, and SQL joins.

## RDBMS Architecture

### Core Capabilities

The database system supports:

- **Table Declaration**: Create tables with specified column data types (INTEGER, VARCHAR, DATE, SERIAL, etc.)
- **Constraints**: Primary keys, unique constraints, and NOT NULL constraints
- **Indexing**: Create indexes on columns for optimized query performance
- **CRUD Operations**: Full support for SELECT, INSERT, UPDATE, and DELETE operations
- **Joins**: Multi-table queries with INNER JOIN, LEFT JOIN, and other join operations
- **Data Integrity**: Foreign key relationships and referential integrity

### REPL Interface

The interactive REPL mode allows users to:
- Execute SQL commands in real-time
- View formatted query results
- Get feedback on operation success or errors
- Work with an intuitive command-line interface

## Features

### Web Application
- View all employees with their department and role information via joined tables
- Create new employee records with automatic database insertion
- Update existing employee information
- Delete employee records with referential integrity
- Clean, responsive UI with modal forms
- Demonstrates practical CRUD operations against the RDBMS

### Database REPL
- Execute SQL queries directly from the command line
- View query results in a formatted table
- Support for all standard SQL operations (SELECT, INSERT, UPDATE, DELETE, CREATE, DROP)
- Interactive prompt with database name and ready indicator
- Error handling and query validation

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
psql -U your_username -d your_database -f Database/mydb.sql
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

1. Update database credentials in `src/main/java/org/mydb/Main.java`:

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
│   └── mydb.sql       # Database schema and sample data
├── src/main/java/org/mydb/
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

Start the REPL with Maven:

```bash
mvn clean compile
mvn compile exec:java
```

Interactive session example:

```
mydb REPL ? type EXIT to quit
mydb=# INSERT INTO departments(department_name) VALUES('Relations')
INSERT 0 1
mydb=# SELECT * FROM vw_employees;
(results displayed in formatted table)
mydb=# INSERT INTO employees (employee_name, company_email, department_role_id, hire_date) VALUES ('John Doe', 'john.doe@company.com', 1, '2024-01-01');
INSERT 0 1
mydb=# UPDATE employees SET company_email = 'john.d@company.com' WHERE employee_id = 5;
UPDATE 1
mydb=# DELETE FROM employees WHERE employee_id = 5;
DELETE 1
mydb=# CREATE INDEX idx_employee_email ON employees(company_email);
CREATE INDEX
mydb=# EXIT
```

The REPL supports all standard PostgreSQL commands and provides immediate feedback on query execution results.

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

## How It Works

### Database Engine Design

The project uses a layered architecture:

1. **DatabaseEngine Interface**: Defines the contract for database operations
2. **RealDbEngine**: Implements database operations using JDBC and PostgreSQL
3. **DbRepl**: Provides the interactive command-line interface, parsing user input and executing queries
4. **QueryResult**: Wraps query results for formatting and display

### Web Application Integration

The Node.js web application connects directly to PostgreSQL via the `pg` driver, demonstrating how a production application would interact with the RDBMS. The application:

- Executes CRUD queries against properly normalized tables
- Leverages database constraints (PRIMARY KEY, UNIQUE, FOREIGN KEY)
- Uses joins to retrieve related data from multiple tables
- Maintains data integrity through referential constraints

## Implementation Notes

- The web application runs on port 1304 by default
- All employee emails are automatically converted to lowercase
- The view `vw_employees` provides a convenient way to query employee data with department and role information
- The Java REPL supports all standard PostgreSQL commands and provides formatted output
- Table constraints (PRIMARY KEY, UNIQUE, NOT NULL) are enforced at the database level
- Indexes can be created to optimize frequently accessed columns
- Data retrieval uses SQL joins to combine related information from multiple tables

## License

This project is part of a personal portfolio demonstrating relational database concepts, SQL execution, and REPL-based system design.