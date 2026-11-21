package com.example.employeemanagement.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class EmployeeRequest {
    @NotBlank(message = "Name is required")
    @Size(max = 80, message = "Name must not exceed 80 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    @Size(max = 120, message = "Email must not exceed 120 characters")
    private String email;

    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.01", message = "Salary must be greater than zero")
    @Digits(integer = 10, fraction = 2, message = "Salary can have at most 2 decimal places")
    private BigDecimal salary;

    @NotNull(message = "Department ID is required")
    @Positive(message = "Department ID must be positive")
    private Long departmentId;

    public String getName() { return name; }
    public String getEmail() { return email; }
    public BigDecimal getSalary() { return salary; }
    public Long getDepartmentId() { return departmentId; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }
}
