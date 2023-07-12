package com.assignment.studentdatamanagement.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Column(name = "image_name")
    private String imageName;
    @Column(name = "image_type")
    private String imageType;

    @Lob
    @Column(name = "image_value",length = 1000)
    private byte[] imageValue;

    @OneToOne(mappedBy = "")
    @JsonBackReference
    private Student student;

    public void changeImageName(int studentId){

    }
}