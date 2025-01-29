package com.example.demo.student;

import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    /**
     * Retrieves a list of students with the specified email address.
     *
     * @param email the email address to search for
     *
     * @return an Optional containing a list of students with the given email,
     * or an empty Optional if no students are found
     */
    Optional<Student> findByEmail(String email);

    /**
     * Retrieves a student with the specified email address, but a different ID.
     *
     * @param email the email address to search for
     * @param id the ID to exclude from the search
     *
     * @return an Optional containing the student with the given email but a different ID,
     * or an empty Optional if no such student is found
     */
    Optional<Student> findByEmailAndIdNot(String email, UUID id);

}

