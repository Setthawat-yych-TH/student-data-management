package com.assignment.studentdatamanagement.controller;

import com.assignment.studentdatamanagement.dto.Grade;
import com.assignment.studentdatamanagement.dto.GradeRequest;
import com.assignment.studentdatamanagement.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/grade")
@RestController
public class GradeController {

    @Autowired
    GradeService gradeService;

    @GetMapping("")
    public String test() {
        return "test";
    }


    @GetMapping("/showGradeById")
    public Grade showGradeById(@RequestParam int gradeId) {
        return gradeService.showGradeById(gradeId);
    }

    @GetMapping("/showGradeAll")
    public List<Grade> showGradeAll() {
        return gradeService.showGradeAll();
    }

    @PostMapping("/createGrade")
    public Grade createGrade(@RequestBody GradeRequest gradeRequest) {
        return gradeService.createGrade(gradeRequest);
    }

    @DeleteMapping("/deleteGradeById")
    public String deleteGradeById(@RequestParam int gradeId) {
        return gradeService.deleteGradeById(gradeId);
    }

    @DeleteMapping("/deleteGradeAll")
    public String deleteGradeAll() {
        return gradeService.deleteGradeAll();
    }
}
