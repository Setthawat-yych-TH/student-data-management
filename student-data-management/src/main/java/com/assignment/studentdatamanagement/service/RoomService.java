package com.assignment.studentdatamanagement.service;

import com.assignment.studentdatamanagement.dto.Grade;
import com.assignment.studentdatamanagement.dto.Room;
import com.assignment.studentdatamanagement.dto.RoomRequest;
import com.assignment.studentdatamanagement.repository.GradeRepository;
import com.assignment.studentdatamanagement.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    GradeRepository gradeRepository;

    public List<Room> showRoomAll() {
        return roomRepository.findAll();
    }

    public Room createRoom(int gradeId,RoomRequest roomRequest) {

        Grade grade = gradeRepository.findById(gradeId).orElseThrow();

        if(!roomRepository.findByRoomNumberAndGrade(roomRequest.getRoomNumber(),grade).isEmpty()) {
            throw new RuntimeException();
        }
        Room room = new Room(roomRequest.getRoomNumber(),grade);
        roomRepository.save(room);
        return room;
    }

    public String deleteRoomById(int roomId) {

        if((roomRepository.findById(roomId).orElseThrow()) != null) {
            roomRepository.deleteById(roomId);
            return roomId + " : Deleted success";
        }
        throw new RuntimeException();

    }

    public String deleteRoomAll() {
        roomRepository.deleteAll();
        resetRoomId();
        return "Deleted all success";
    }
    public void resetRoomId() {
        roomRepository.resetRoomId();
    }

}
