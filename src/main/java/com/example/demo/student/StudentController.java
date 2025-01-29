package com.example.demo.student;

import com.example.demo.model.Student;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Handles GET requests to retrieve all students.
     *
     * @return a list of all students
     */
    @GetMapping
    public List<Student> getAllSudents() {
        return this.studentService.getAllSudents();
    }

    /**
     * Handles POST requests to register a new student.
     *
     * @param student the student object to be registered, validated from the request body
     */
    @PostMapping
    public void registerNewStudent(@Valid @RequestBody Student student) {
        this.studentService.addNewStudent(student);
    }

    /**
     * Handles DELETE requests to delete a student.
     *
     * @param studentId the ID of the student to be deleted, extracted from the URL path
     *
     * @throws IllegalStateException if the student with the given ID does not exist
     */
    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable("studentId") String studentId) {
        this.studentService.deleteStudent(UUID.fromString(studentId));
    }

    /**
     * Handles PUT requests to update a student.
     *
     * @param studentId the ID of the student to be updated, extracted from the URL path
     * @param student   the updated student object, validated from the request body
     *
     * @throws IllegalStateException if the student with the given ID does not exist
     */
    @PutMapping("/{studentId}")
    public void updateStudent(@PathVariable("studentId") String studentId, @Valid @RequestBody Student student) {
        this.studentService.updateStudent(UUID.fromString(studentId), student);
    }

}
