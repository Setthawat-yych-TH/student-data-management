package com.assignment.studentdatamanagement.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.beans.ConstructorProperties;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "student_Number")
    private Integer studentNo;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_login")
    private String userLogin;

    @Column(name = "password")
    private String password;

    @Column(name = "school")
    private String school;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "started_date")
    private LocalDate startedDate;

    @Column(name = "resigned_date")
    private LocalDate resignedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusType status;



    public Student(String email,String firstName, String lastName,String userLogin, String password, String school, Room room, LocalDate startedDate, LocalDate resignedDate, StatusType status) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userLogin = userLogin;
        this.password = password;
        this.school = school;
        this.room = room;
        this.startedDate = startedDate;
        this.resignedDate = resignedDate;
        this.status = status;
    }

    public int changeStudentIdFormat() {
       return Integer.valueOf(this.startedDate.getYear()+(String.format("%04d", studentId)));
    }



    public enum StatusType{
        ACTIVE, INACTIVE
    }
}

