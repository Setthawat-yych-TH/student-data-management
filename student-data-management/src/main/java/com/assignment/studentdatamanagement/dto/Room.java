package com.assignment.studentdatamanagement.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer roomId;


    @Column(name = "room_number")
    private Integer roomNumber;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "grade_id")
    private Grade grade;

    @OneToMany(mappedBy = "room")
    @JsonManagedReference
    private Set<Student> studentSet;



    public Room() {

    }

    public Room(Integer roomNumber, Grade grade) {
        this.roomNumber = roomNumber;
        this.grade = grade;
    }

}
