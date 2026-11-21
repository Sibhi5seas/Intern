CREATE DATABASE IF NOT EXISTS employee_management;
USE employee_management;

CREATE TABLE IF NOT EXISTS departments (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(80) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS employees (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(80) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    salary DECIMAL(12, 2) NOT NULL,
    department_id BIGINT NOT NULL,
    CONSTRAINT fk_employee_department FOREIGN KEY (department_id) REFERENCES departments(id)
);
