package com.assignment.studentdatamanagement.repository;

import com.assignment.studentdatamanagement.dto.Grade;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository <Grade, Integer> {

    public List<Grade> findByGradeDesc (String gradeDesc);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "ALTER SEQUENCE grade_id_seq RESTART WITH 1")
    public int resetGradeId();


}
