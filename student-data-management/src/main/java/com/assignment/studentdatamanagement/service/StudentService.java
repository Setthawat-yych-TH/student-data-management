package com.assignment.studentdatamanagement.service;

import com.assignment.studentdatamanagement.dto.Room;
import com.assignment.studentdatamanagement.dto.Student;
import com.assignment.studentdatamanagement.dto.StudentRequest;
import com.assignment.studentdatamanagement.dto.StudentResponse;
import com.assignment.studentdatamanagement.repository.RoomRepository;
import com.assignment.studentdatamanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RoomRepository roomRepository;


    public List<Student> searchStudent(String type, String value) {
        try {
            Student student = new Student();
            Class<? extends Student> c = student.getClass();

            String typeFormatted = type.substring(0, 1).toUpperCase() + type.substring(1);
            Method getAttribute = c.getMethod("get" + typeFormatted);

            List<Student> studentList = studentRepository.findAll();
            List<Student> searchStudentList = new ArrayList<>();
            for (Student student1 : studentList) {
                String valueObject = (String) getAttribute.invoke(student1);

                if(valueObject.contains(value))
                    searchStudentList.add(student1);
            }
            return searchStudentList;
        }

        catch (Exception e){
            throw new RuntimeException(e);
        }

    }


    public List<Student> searchStudentByFirstName(String firstName) {

        return studentRepository.findByFirstNameContainingIgnoreCase(firstName);
    }


    public StudentResponse showStudentById(int studentId) {

        Student student = studentRepository.findById(studentId).orElseThrow();

        return transferStudentToStudentResponse(student);
    }

    public List<StudentResponse> showStudentAll(){
        List <Student> studentList = studentRepository.findAll();
        List <StudentResponse> studentResponseList = new ArrayList<>();

        for (Student student : studentList) {
            studentResponseList.add(transferStudentToStudentResponse(student));
        }

        return studentResponseList;
    }


    public Student createStudent(StudentRequest studentRequest, int roomId){

        Room room = roomRepository.findById(roomId).orElseThrow();

        if(!studentRepository.findByFirstnameAndLastname(formatName(studentRequest.getFirstName()),formatName(studentRequest.getLastName())).isEmpty()) {
           throw new RuntimeException("Student duplicate found");
        }

        Student student = new Student(
                createEmailFormat(studentRequest.getFirstName(),studentRequest.getLastName(),studentRequest.getSchool()),
                formatName(studentRequest.getFirstName()),
                formatName(studentRequest.getLastName()),
                createUserLoginFormat(studentRequest.getFirstName(),studentRequest.getLastName()),
                studentRequest.getPassword(),
                studentRequest.getSchool(),
                room,
                studentRequest.getStartedDate(),
                studentRequest.getResignedDate(),
                checkStatusIsActive(studentRequest));
        studentRepository.save(student);

        student.setStudentNo(student.changeStudentIdFormat());
        studentRepository.save(student);

        return student;
    }

    public Student updateStudentById(int studentId, StudentRequest studentRequest) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        student.setFirstName(formatName(studentRequest.getFirstName()));
        student.setLastName(formatName(studentRequest.getLastName()));
        student.setPassword(studentRequest.getPassword());
        student.setUserLogin(createUserLoginFormat(studentRequest.getFirstName(),studentRequest.getLastName()));
        student.setStartedDate(studentRequest.getStartedDate());
        student.setResignedDate(studentRequest.getResignedDate());
        student.setStatus(checkStatusIsActive(studentRequest));
        return studentRepository.save(student);
    }

    public String deleteStudentById(int studentId) {
        if((studentRepository.findById(studentId).orElseThrow()) != null) {
            studentRepository.deleteById(studentId);
            return studentId + " : Deleted success";
        }
        throw new RuntimeException();
    }

    public String deleteStudentAll() {
        studentRepository.deleteAll();
        resetStudentId();
        return "Deleted all success";
    }
    public void resetStudentId() {
        studentRepository.resetStudentId();
    }


    private Student.StatusType checkStatusIsActive(StudentRequest studentRequest) {
        if (studentRequest.getResignedDate() != null)
        {
            if (studentRequest.getResignedDate().isAfter(LocalDate.now()))
                return Student.StatusType.ACTIVE;
            else
                return Student.StatusType.INACTIVE;
        }
        return Student.StatusType.ACTIVE;
        }


        private StudentResponse transferStudentToStudentResponse(Student student) {

            StudentResponse studentResponse = new StudentResponse();
            studentResponse.setStudentId(student.getStudentId());
            studentResponse.setStudentNo(student.getStudentNo());
            studentResponse.setEmail(student.getEmail());
            studentResponse.setFirstName(student.getFirstName());
            studentResponse.setLastName(student.getLastName());
            studentResponse.setUserLogin(student.getUserLogin());
            studentResponse.setSchool(student.getSchool());
            studentResponse.setGrade(student.getRoom().getGrade().getGradeDesc());
            studentResponse.setRoomNumber(student.getRoom().getRoomNumber());
            studentResponse.setStartedDate(student.getStartedDate());
            studentResponse.setResignedDate(student.getResignedDate());
            studentResponse.setStatus(student.getStatus());

            return studentResponse;
        }








    public String formatName(String name){
        return name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase();
    }


    public String createEmailFormat(String firstName, String lastName, String school) {
        int selectLastNameChar = 0;
        String lastnameChar = String.valueOf(lastName.toLowerCase().charAt(selectLastNameChar));
        String emailFormatted = firstName.toLowerCase() + "." + lastnameChar + "@" +school.toLowerCase()+".co.th";
        while (!studentRepository.findByEmail(emailFormatted).isEmpty()) {
            selectLastNameChar = selectLastNameChar + 1;
            lastnameChar = lastnameChar + lastName.toLowerCase().charAt(selectLastNameChar);
            emailFormatted = firstName.toLowerCase() + "." + lastnameChar + "@" +school.toLowerCase()+".co.th";

        }
        return emailFormatted;
    }

    public String createUserLoginFormat(String firstName, String lastName) {
        int selectLastNameChar = 0;
        String lastnameChar = String.valueOf(lastName.toLowerCase().charAt(selectLastNameChar));
        String userLoginFormatted = firstName.toLowerCase() + "." + lastnameChar.charAt(selectLastNameChar);
        while (!studentRepository.findByUserLogin(userLoginFormatted).isEmpty()) {
            selectLastNameChar = selectLastNameChar + 1;
            lastnameChar = lastnameChar + lastName.toLowerCase().charAt(selectLastNameChar);
            userLoginFormatted = firstName.toLowerCase() + "." + lastnameChar;
        }
        return userLoginFormatted;
    }


}
