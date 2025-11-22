package org.example.springbootdemo.service;


import org.example.springbootdemo.entity.Student;
import org.example.springbootdemo.repository.StudentRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;


    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    // CREATE or UPDATE
    public Student saveStudent(Student student) {
        return repository.save(student);
    }


    @CachePut(value = "students", key = "#student.id")
    public List<Student> saveStudents(List<Student> students) {
        return repository.saveAll(students);  // JPA automatically handles insert + update
    }

    // GET ALL
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // GET BY ID
    @Cacheable(value = "students", key = "#id")
    public Student getStudentById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // DELETE
    @CacheEvict(value = "students", key = "#id")
    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }
}
