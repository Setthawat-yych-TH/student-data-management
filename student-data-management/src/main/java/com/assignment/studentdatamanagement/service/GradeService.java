package com.assignment.studentdatamanagement.service;

import com.assignment.studentdatamanagement.dto.Grade;
import com.assignment.studentdatamanagement.dto.GradeRequest;
import com.assignment.studentdatamanagement.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    GradeRepository gradeRepository;

    public List<Grade> showGradeAll() {
        return gradeRepository.findAll();
    }

    public Grade showGradeById(int gradeId) {

        return gradeRepository.findById(gradeId).orElseThrow();
    }

    public Grade createGrade(GradeRequest gradeRequest) {
        if(!gradeRepository.findByGradeDesc(gradeRequest.getGradeDesc()).isEmpty()) {
            throw  new RuntimeException();
        }

        Grade grade = new Grade(gradeRequest.getGradeDesc());
        gradeRepository.save(grade);
        return grade;
    }


    public String deleteGradeById(int gradeId) {

        if((gradeRepository.findById(gradeId).orElseThrow()) != null) {
            gradeRepository.deleteById(gradeId);
            return gradeId + " : Deleted success";
        }
        throw new RuntimeException();

    }

    public String deleteGradeAll() {
        gradeRepository.deleteAll();
        resetGradeId();
        return "Deleted all success";
    }
    public void resetGradeId() {
        gradeRepository.resetGradeId();
    }


}
