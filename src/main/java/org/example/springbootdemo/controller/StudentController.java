package org.example.springbootdemo.controller;


import org.example.springbootdemo.entity.Student;
import org.example.springbootdemo.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // ADD Student
    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        return service.saveStudent(student);
    }

    // Bulk insert or update
    @PostMapping("/bulk")
    public List<Student> saveStudents(@RequestBody List<Student> students) {
        return service.saveStudents(students);
    }


    // GET ALL Students
    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }

    // GET Student by ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return service.getStudentById(id);
    }

    // DELETE by ID
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return "Student deleted with id: " + id;
    }
}
