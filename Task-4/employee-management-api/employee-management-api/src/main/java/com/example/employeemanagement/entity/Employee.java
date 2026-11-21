package com.example.employeemanagement.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "employees", uniqueConstraints = {
        @UniqueConstraint(name = "uk_employee_email", columnNames = "email")
})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal salary;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    public Employee() { }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public BigDecimal getSalary() { return salary; }
    public Department getDepartment() { return department; }
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }
    public void setDepartment(Department department) { this.department = department; }
}
