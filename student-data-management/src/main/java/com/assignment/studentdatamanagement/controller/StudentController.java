package com.assignment.studentdatamanagement.controller;

import com.assignment.studentdatamanagement.dto.Student;
import com.assignment.studentdatamanagement.dto.StudentRequest;
import com.assignment.studentdatamanagement.dto.StudentResponse;
import com.assignment.studentdatamanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;

@RequestMapping("/student")
@RestController
public class StudentController {

    @Autowired
    StudentService studentService;
    @GetMapping("")
    public String test(){
        return String.valueOf(Student.StatusType.ACTIVE);
    }



    @GetMapping("/searchStudent")
    public List<Student> searchStudent(@RequestParam String type, String value){
        return studentService.searchStudent(type, value);
    }
    @GetMapping("/searchStudentByFirstName")
    public List<Student> searchStudentByNameFirstName(@RequestParam String firstName) {
        return studentService.searchStudentByFirstName(firstName);
    }


    @GetMapping("/showStudentById")
    public StudentResponse showStudentById(@RequestParam int studentId) {
        return studentService.showStudentById(studentId);
    }

    @GetMapping("/showStudentAll")
    public List<StudentResponse> showStudentAll(){
        return studentService.showStudentAll();
    }

    @PostMapping("/createStudent")
    public Student createStudent(@RequestBody StudentRequest studentRequest,@RequestParam int roomId){
        return studentService.createStudent(studentRequest, roomId);
    }

    @PutMapping("/updateStudent")
    public Student updateStudent(@RequestParam int studentId, @RequestBody StudentRequest studentRequest) {
        return studentService.updateStudentById(studentId, studentRequest);
    }

    @DeleteMapping("/deleteStudentById")
    public String deleteStudentById(@RequestParam int studentId){
        return studentService.deleteStudentById(studentId);
    }

    @DeleteMapping("/deleteStudentAll")
    public String deleteStudentAll() {
        return studentService.deleteStudentAll();
    }

}
