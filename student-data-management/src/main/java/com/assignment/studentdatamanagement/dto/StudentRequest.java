package com.assignment.studentdatamanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class StudentRequest {

    private String firstName;

    private String lastName;

    private String password;

    private String school;

    private Integer roomId;

    private LocalDate startedDate;

    private LocalDate resignedDate;

}
