package com.example.studentcrud;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// This controller shows CRUD using an ArrayList instead of MySQL.
@RestController
@RequestMapping("/memory/students")
public class MemoryStudentController {

    private List<Student> students = new ArrayList<>();
    private int nextId = 1;

    @GetMapping
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return ResponseEntity.ok(s);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        student.setId(nextId);
        nextId++;

        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id,
                                                  @RequestBody Student newStudent) {
        for (Student s : students) {
            if (s.getId() == id) {
                s.setName(newStudent.getName());
                s.setDepartment(newStudent.getDepartment());
                s.setAge(newStudent.getAge());

                return ResponseEntity.ok(s);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                students.remove(s);
                return ResponseEntity.noContent().build();
            }
        }

        return ResponseEntity.notFound().build();
    }
}
