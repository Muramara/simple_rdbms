CREATE TABLE departments (
    department_id SERIAL PRIMARY KEY,
    department_name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE department_roles (
    department_role_id SERIAL PRIMARY KEY,
    department_id INTEGER REFERENCES departments NOT NULL,
    department_role_name VARCHAR(50) NOT NULL
);

CREATE INDEX dept_role_dept_id ON department_roles(department_id);

CREATE TABLE employees (
    employee_id SERIAL PRIMARY KEY,
    employee_name VARCHAR(50) NOT NULL,
    company_email VARCHAR(100) UNIQUE NOT NULL,
    department_role_id INTEGER REFERENCES department_roles NOT NULL,
    hire_date DATE NOT NULL
);

CREATE INDEX emp_dept_role_id ON employees(department_role_id);

CREATE VIEW vw_employees AS
SELECT 
    e.employee_id,
    e.employee_name,
    e.company_email,
    e.hire_date,
    dr.department_role_name,
    d.department_name
FROM employees e
JOIN department_roles dr ON e.department_role_id = dr.department_role_id
JOIN departments d ON dr.department_id = d.department_id
ORDER BY e.employee_id;

-- example data manipulation statements

INSERT INTO departments (department_name) VALUES
('Human Resources'),
('Engineering'),
('Marketing'),
('Sales');

INSERT INTO department_roles (department_id, department_role_name) VALUES
(1, 'HR Manager'),
(1, 'Recruiter'),
(2, 'Software Engineer'),
(2, 'DevOps Engineer'),
(3, 'Marketing Specialist'),
(3, 'Content Creator'),
(4, 'Sales Executive'),
(4, 'Account Manager');

INSERT INTO employees (employee_name, company_email, department_role_id, hire_date) VALUES
('Alice Johnson', 'alice.johnson@company.com', 1, '2023-01-15'),
('Bob Smith', 'bob.smith@company.com', 2, '2023-02-20'),
('Charlie Brown', 'charlie.brown@company.com', 3, '2023-03-10'),
('Diana Prince', 'diana.prince@company.com', 4, '2023-04-05');

UPDATE employees SET company_email = LOWER(company_email);

DELETE FROM employees WHERE hire_date < '2023-02-01';