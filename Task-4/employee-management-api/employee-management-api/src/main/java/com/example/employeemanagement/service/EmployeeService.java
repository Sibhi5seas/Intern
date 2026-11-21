package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.EmployeeRequest;
import com.example.employeemanagement.dto.EmployeeResponse;
import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.exception.DuplicateEmailException;
import com.example.employeemanagement.exception.ResourceNotFoundException;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Locale;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public EmployeeResponse createEmployee(EmployeeRequest request) {
        String email = normalizeEmail(request.getEmail());
        if (employeeRepository.existsByEmailIgnoreCase(email)) {
            throw new DuplicateEmailException("An employee with this email already exists");
        }
        Department department = findDepartment(request.getDepartmentId());
        Employee employee = new Employee();
        employee.setName(request.getName().trim());
        employee.setEmail(email);
        employee.setSalary(request.getSalary());
        employee.setDepartment(department);
        return toResponse(employeeRepository.save(employee));
    }

    @Transactional(readOnly = true)
    public Page<EmployeeResponse> getEmployees(String name, Long departmentId, Pageable pageable) {
        Page<Employee> employees;
        boolean hasName = name != null && !name.isBlank();
        boolean hasDepartment = departmentId != null;
        if (hasName && hasDepartment) {
            employees = employeeRepository.findByNameContainingIgnoreCaseAndDepartment_Id(name.trim(), departmentId, pageable);
        } else if (hasName) {
            employees = employeeRepository.findByNameContainingIgnoreCase(name.trim(), pageable);
        } else if (hasDepartment) {
            employees = employeeRepository.findByDepartment_Id(departmentId, pageable);
        } else {
            employees = employeeRepository.findAll(pageable);
        }
        return employees.map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public EmployeeResponse getEmployeeById(Long id) {
        return toResponse(findEmployee(id));
    }

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {
        Employee employee = findEmployee(id);
        String email = normalizeEmail(request.getEmail());
        if (employeeRepository.existsByEmailIgnoreCaseAndIdNot(email, id)) {
            throw new DuplicateEmailException("Another employee is already registered with this email");
        }
        Department department = findDepartment(request.getDepartmentId());
        employee.setName(request.getName().trim());
        employee.setEmail(email);
        employee.setSalary(request.getSalary());
        employee.setDepartment(department);
        return toResponse(employeeRepository.save(employee));
    }

    public void deleteEmployee(Long id) {
        Employee employee = findEmployee(id);
        employeeRepository.delete(employee);
    }

    private Employee findEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    private Department findDepartment(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
    }

    private String normalizeEmail(String email) {
        return email.trim().toLowerCase(Locale.ROOT);
    }

    private EmployeeResponse toResponse(Employee employee) {
        Department department = employee.getDepartment();
        return new EmployeeResponse(employee.getId(), employee.getName(), employee.getEmail(),
                employee.getSalary(), department.getId(), department.getName());
    }
}
