package com.example.demo.student;

import com.example.demo.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Retrieves all students from the database.
     *
     * @return a list of all students
     */
    public List<Student> getAllSudents() {
        return this.studentRepository.findAll();
    }

    /**
     * Registers a new student with the database.
     *
     * @param student the student to be registered
     *
     * @throws IllegalStateException if the email already exists in the database
     */
    public void addNewStudent(Student student) {
        this.validateEmail(student.getEmail());

        // Save the new student to the database
        this.studentRepository.save(student);
    }

    /**
     * Deletes a student from the database.
     *
     * @param studentId the ID of the student to be deleted
     *
     * @throws IllegalStateException if the student with the given ID does not exist
     */
    public void deleteStudent(UUID studentId) {
        // Validate if the student exists in the database
        this.validateStudent(studentId);

        // Proceed to delete the student from the database
        this.studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(UUID studentId, Student student) {
        this.validateStudent(studentId);

        // Ensure the email isn't taken by another student
        Optional<Student> emailTaken = this.studentRepository.findByEmailAndIdNot(student.getEmail(), studentId);
        if (emailTaken.isPresent()) {
            throw new IllegalStateException("Email already exists");
        }

        // Update the student with the validated email
        student.setId(studentId);
        this.studentRepository.save(student);
    }

    /**
     * Validates if an email already exists in the database. This is useful to prevent duplicate emails when registering
     * a new student.
     *
     * @param email the email to be validated
     *
     * @throws IllegalStateException if the email already exists in the database
     */
    private void validateEmail(String email) {
        // Check if a student with the same email already exists
        final Optional<Student> emailTaken = this.studentRepository.findByEmail(email);
        if (emailTaken.isPresent()) {
            // Throw an exception if the email is already in use
            throw new IllegalStateException("Email already exists");
        }
    }

    /**
     * Validates if a student exists in the database.
     *
     * @param studentId the ID of the student to be validated
     *
     * @throws IllegalStateException if the student with the given ID does not exist
     */
    private void validateStudent(UUID studentId) {
        // Check if the student exists
        boolean exists = this.studentRepository.existsById(studentId);
        if (! exists) {
            // Throw an exception if the student does not exist
            throw new IllegalStateException("Student with id " + studentId + " does not exist");
        }
    }

}
