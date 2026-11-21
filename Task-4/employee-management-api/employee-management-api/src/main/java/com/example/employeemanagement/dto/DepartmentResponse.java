package com.example.employeemanagement.dto;

public class DepartmentResponse {
    private final Long id;
    private final String name;

    public DepartmentResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
}
