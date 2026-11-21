package com.example.employeemanagement.dto;

import java.math.BigDecimal;

public class EmployeeResponse {
    private final Long id;
    private final String name;
    private final String email;
    private final BigDecimal salary;
    private final Long departmentId;
    private final String departmentName;

    public EmployeeResponse(Long id, String name, String email, BigDecimal salary,
                            Long departmentId, String departmentName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public BigDecimal getSalary() { return salary; }
    public Long getDepartmentId() { return departmentId; }
    public String getDepartmentName() { return departmentName; }
}
