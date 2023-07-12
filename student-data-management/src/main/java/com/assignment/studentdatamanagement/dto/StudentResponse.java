package com.assignment.studentdatamanagement.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentResponse {

    private int studentId;

    private Integer studentNo;

    private String email;

    private String firstName;

    private String lastName;

    private String userLogin;

    private String school;

    private String grade;

    private int roomNumber;

    private LocalDate startedDate;

    private LocalDate resignedDate;

    private Student.StatusType status;

    private String imageName;

    private String imageType;

    private byte[] imageValue;

}
