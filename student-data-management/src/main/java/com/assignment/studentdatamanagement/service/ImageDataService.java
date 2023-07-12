package com.assignment.studentdatamanagement.service;

import com.assignment.studentdatamanagement.dto.ImageData;
import com.assignment.studentdatamanagement.dto.Student;
import com.assignment.studentdatamanagement.repository.ImageRepository;
import com.assignment.studentdatamanagement.repository.StudentRepository;
import com.assignment.studentdatamanagement.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private StudentRepository studentRepository;

    public String uploadImage(MultipartFile file, int studentId) throws IOException {
        Student student = studentRepository.findById(studentId).orElseThrow();
        ImageData imageData = imageRepository.save(ImageData.builder()
                .imageName(file.getName()+studentId+".jpg")
                .imageType(file.getContentType())
                .imageValue(ImageUtils.compressImage(file.getBytes())).build());


        imageData.setStudent(student);
        imageRepository.save(imageData);
        student.setImageData(imageData);
        studentRepository.save(student);
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        throw new RuntimeException("Image can't use");
    }

    public byte[] getImage(String fileName){
        Optional<ImageData> dbImageData = imageRepository.findByImageName(fileName);
        byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageValue());
        return images;
    }
}