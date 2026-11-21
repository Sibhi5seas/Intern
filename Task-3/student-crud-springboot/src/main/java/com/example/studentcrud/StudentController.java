package com.example.studentcrud;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository repo;

    public StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    // Get all students. A name can also be given as a query parameter.
    @GetMapping
    public List<Student> getStudents(@RequestParam(required = false) String name) {
        List<Student> students = repo.findAll();

        if (name == null || name.isEmpty()) {
            return students;
        }

        return students.stream()
                .filter(s -> s.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    // Get one student using the id in the URL.
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) {
        Optional<Student> student = repo.findById(id);

        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        }

        return ResponseEntity.notFound().build();
    }

    // Add a student. The details come from the request body.
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student saved = repo.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Update an existing student.
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id,
                                                  @RequestBody Student newStudent) {
        Optional<Student> oldStudent = repo.findById(id);

        if (oldStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Student student = oldStudent.get();
        student.setName(newStudent.getName());
        student.setDepartment(newStudent.getDepartment());
        student.setAge(newStudent.getAge());

        return ResponseEntity.ok(repo.save(student));
    }

    // Delete a student.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
