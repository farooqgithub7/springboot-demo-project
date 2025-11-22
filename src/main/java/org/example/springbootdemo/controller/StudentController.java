package org.example.springbootdemo.controller;


import org.example.springbootdemo.entity.Student;
import org.example.springbootdemo.service.KafkaProducer;
import org.example.springbootdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {


    @Autowired
    private StudentService studentService;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }



    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student) {
        Student saved = studentService.saveStudent(student);
        kafkaProducer.sendMessage("student-topic", "New student created: " + saved.getName());
        return saved;
    }

    @PostMapping("/save")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        Student saved = service.saveStudent(student);
        return ResponseEntity.status(201).body(saved); // 201 CREATED
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
