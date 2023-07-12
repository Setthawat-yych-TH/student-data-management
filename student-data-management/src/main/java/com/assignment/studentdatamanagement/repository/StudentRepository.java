package com.assignment.studentdatamanagement.repository;

import com.assignment.studentdatamanagement.dto.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    public Student findByStudentNo (Integer studentNo);

    public List<Student> findByFirstNameContainingIgnoreCase (String firstName);

    public List<Student> findByEmail (String email);

    public List<Student> findByUserLogin (String userLogin);

    @Query(nativeQuery = true, value = "SELECT * FROM students WHERE first_name = :firstName and last_name = :lastName")
    public List<Student> findByFirstnameAndLastname (String firstName, String lastName);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "ALTER SEQUENCE student_id_seq RESTART WITH 1")
    public int resetStudentId();

}
