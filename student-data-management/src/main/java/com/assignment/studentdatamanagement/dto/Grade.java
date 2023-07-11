package com.assignment.studentdatamanagement.dto;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Integer gradeId;

    @Column(name = "grade_desc")
    private String gradeDesc;

    @OneToMany(mappedBy = "grade")
    @JsonManagedReference
    private Set<Room> roomSet;

    public Grade() {

    }

    public Grade(String gradeDesc) {
        this.gradeDesc = gradeDesc;
    }
}
