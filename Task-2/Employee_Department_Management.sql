-- PART 1: DATABASE DESIGN : I used create database command to create a new database named company_db2 and then use the USE command to select that database for subsequent operations.
CREATE DATABASE company_db2;
USE company_db2;

-- Create Department table : created them with given columns and data types.
CREATE TABLE Department (
    department_id INT PRIMARY KEY,
    department_name VARCHAR(50),
    location VARCHAR(50)
);

-- Create Employee table : did the same for Employee table and also added a foreign key constraint to link the department_id in Employee table to the department_id in Department table.
CREATE TABLE Employee (
    employee_id INT PRIMARY KEY,
    employee_name VARCHAR(50),
    email VARCHAR(100) UNIQUE,
    salary DECIMAL(10,2),
    joining_date DATE,
    department_id INT,
    FOREIGN KEY (department_id)
    REFERENCES Department(department_id)
);

-- Insert department records : entered sample values into the Department table.
INSERT INTO Department VALUES
(1, 'IT', 'Chennai'),
(2, 'HR', 'Bangalore'),
(3, 'Finance', 'Mumbai'),
(4, 'Marketing', 'Hyderabad'),
(5, 'Sales', 'Coimbatore');

-- Insert employee records : entered sample values into the Employee table.
INSERT INTO Employee VALUES
(101, 'Arun', 'arun@gmail.com', 45000, '2022-01-15', 1),
(102, 'Priya', 'priya@gmail.com', 55000, '2021-03-10', 1),
(103, 'Karthik', 'karthik@gmail.com', 35000, '2023-06-20', 1),
(104, 'Divya', 'divya@gmail.com', 28000, '2024-02-12', 1),

(105, 'Rahul', 'rahul@gmail.com', 40000, '2022-05-18', 2),
(106, 'Sneha', 'sneha@gmail.com', 32000, '2023-08-25', 2),
(107, 'Vikram', 'vikram@gmail.com', 50000, '2021-11-05', 2),

(108, 'Meena', 'meena@gmail.com', 60000, '2020-04-15', 3),
(109, 'Sanjay', 'sanjay@gmail.com', 42000, '2022-09-12', 3),
(110, 'Anitha', 'anitha@gmail.com', 38000, '2023-01-30', 3),

(111, 'Ravi', 'ravi@gmail.com', 30000, '2024-03-15', 4),
(112, 'Kavya', 'kavya@gmail.com', 45000, '2022-07-22', 4),

(113, 'Manoj', 'manoj@gmail.com', 35000, '2023-10-10', 5),
(114, 'Swathi', 'swathi@gmail.com', 48000, '2021-12-01', 5),
(115, 'Ajay', 'ajay@gmail.com', 25000, '2024-05-05', 5);


-- PART 2: SQL QUERIES

-- 1. Display all employees : used * to select all columns from the Employee table.
SELECT * FROM Employee;

-- 2. Display employees in the IT department :used a JOIN to combine Employee and Department tables and filtered for the IT department.
SELECT e.employee_id, e.employee_name, e.email, e.salary
FROM Employee AS e
JOIN Department AS d ON d.department_id = e.department_id
WHERE d.department_name = 'IT';

-- 3. Display employees with a salary greater than 30000 : used a WHERE command to filter employees based on their salary.
SELECT employee_id, employee_name, email, salary
FROM Employee
WHERE salary > 30000;

-- 4. Sort employees by salary from highest to lowest : used ORDER BY to sort the results in descending order based on salary.
SELECT employee_id, employee_name, salary
FROM Employee
ORDER BY salary DESC;

-- 5. Display employee names with their department names : used a JOIN to combine Employee and Department tables and selected the employee names along with their corresponding department names.
SELECT e.employee_id, e.employee_name, d.department_name
FROM Employee AS e
JOIN Department AS d ON d.department_id = e.department_id
ORDER BY d.department_name, e.employee_name;

-- 6. Count employees in each department : first used a LEFT JOIN to include all departments, even those without employees, and then used COUNT to count the number of employees in each department.
SELECT d.department_name, COUNT(e.employee_id) AS employee_count
FROM Department AS d
LEFT JOIN Employee AS e ON e.department_id = d.department_id
GROUP BY d.department_id, d.department_name
ORDER BY d.department_name;

-- 7. Calculate the average salary in each department : first grouped the results by department and then used AVG to calculate the average salary for each department.
SELECT d.department_name, ROUND(AVG(e.salary), 2) AS average_salary
FROM Department AS d
JOIN Employee AS e ON e.department_id = d.department_id
GROUP BY d.department_id, d.department_name
ORDER BY average_salary DESC;

-- 8. Display employees who joined on or after January 1, 2022 : used simple WHERE command to filter employees.
SELECT employee_id, employee_name, joining_date
FROM Employee
WHERE joining_date >= '2022-01-01'
ORDER BY joining_date;

-- 9. Find employees whose salary is between 40000 and 70000 : used between and operator to filter employees based on their salary range.
SELECT employee_id, employee_name, salary
FROM Employee
WHERE salary BETWEEN 40000 AND 70000
ORDER BY salary;

-- 10. Display employees whose name begins with 'A': like operator is used to filter employees whose names start with the letter 'A'.
SELECT employee_id, employee_name, email, salary
FROM Employee
WHERE employee_name LIKE 'A%';


-- PART 3: ADVANCED SQL

-- 11. Find the highest-paid employee(s) in each department : used a subquery to find the maximum salary in each department and then selected employees whose salary matches that maximum.
SELECT
    d.department_name,
    e.employee_id,
    e.employee_name,
    e.salary
FROM Employee AS e
JOIN Department AS d ON d.department_id = e.department_id
WHERE e.salary = (
    SELECT MAX(e2.salary)
    FROM Employee AS e2
    WHERE e2.department_id = e.department_id
)
ORDER BY d.department_name;

-- 12. Find employees earning more than the company-wide average salary : took the average salary of all employees and then selected those employees whose salary is greater than that average.
SELECT employee_id, employee_name, salary
FROM Employee
WHERE salary > (SELECT AVG(salary) FROM Employee)
ORDER BY salary DESC;

-- 13. Find departments whose average salary is greater than 40000 : grouped the results by department and used HAVING to segregate departments based on their average salary.
SELECT d.department_name, ROUND(AVG(e.salary), 2) AS average_salary
FROM Department AS d
JOIN Employee AS e ON e.department_id = d.department_id
GROUP BY d.department_id, d.department_name
HAVING AVG(e.salary) > 40000
ORDER BY average_salary DESC;

-- 14. Rank employees by salary within each department : used the rank function to assign a rank to each employee based on their salary within their respective departments.
SELECT
    d.department_name,
    e.employee_id,
    e.employee_name,
    e.salary,
    RANK() OVER (
        PARTITION BY e.department_id
        ORDER BY e.salary DESC
    ) AS salary_rank
FROM Employee AS e
JOIN Department AS d ON d.department_id = e.department_id
ORDER BY d.department_name, salary_rank;

-- 15. Use a CTE to list department payroll totals : used with command to create a common table expression (CTE) that calculates the total payroll for each department and then I joined it with the Department table to display department names along with their total payroll.
WITH department_payroll AS (
    SELECT department_id, SUM(salary) AS total_payroll
    FROM Employee
    GROUP BY department_id
)
SELECT d.department_name, dp.total_payroll
FROM department_payroll AS dp
JOIN Department AS d ON d.department_id = dp.department_id
ORDER BY dp.total_payroll DESC;


-- PART 4: DATABASE OPERATIONS

-- UPDATE: Give employee 110 (Anitha) a salary increase : used update keyword
UPDATE Employee
SET salary = 40000.00
WHERE employee_id = 110;

-- DELETE: Remove employee 115 (Ajay) : used delete keyword
DELETE FROM Employee
WHERE employee_id = 115;

-- ALTER TABLE: Add an optional phone number column : used alter table keyword to add a column.
ALTER TABLE Employee
ADD COLUMN phone_number VARCHAR(20) NULL;

-- CREATE INDEX: Speed up salary filtering and sorting : used to create an index on the salary column to improve performance for queries that filter or sort by salary.
CREATE INDEX idx_employee_salary
ON Employee (salary);