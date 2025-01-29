package com.example.demo.student;

import com.example.demo.model.Student;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllSudents() {
        return this.studentService.getAllSudents();
    }

    @PostMapping
    public void registerNewStudent(@Valid @RequestBody Student student) {
        this.studentService.addNewStudent(student);
    }
}
