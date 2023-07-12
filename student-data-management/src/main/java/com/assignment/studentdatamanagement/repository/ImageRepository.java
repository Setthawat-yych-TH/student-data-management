package com.assignment.studentdatamanagement.repository;

import com.assignment.studentdatamanagement.dto.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageData, Long> {

    @Override
    Optional<ImageData> findById(Long aLong);

    Optional<ImageData> findByImageName(String imageName);
}
