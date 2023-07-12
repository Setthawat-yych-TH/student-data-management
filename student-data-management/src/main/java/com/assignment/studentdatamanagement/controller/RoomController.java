package com.assignment.studentdatamanagement.controller;

import com.assignment.studentdatamanagement.dto.Room;
import com.assignment.studentdatamanagement.dto.RoomRequest;
import com.assignment.studentdatamanagement.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/room")
@RestController
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping("/showRoomAll")
    public List<Room> showRoomAll(){
        return roomService.showRoomAll();
    }

    @PostMapping("/createRoom")
    public Room createRoom(@RequestParam int gradeId,@RequestBody RoomRequest roomRequest) {
        return roomService.createRoom(gradeId, roomRequest);
    }

    @DeleteMapping("/deleteRoomById")
    public String deleteRoomById(@RequestParam int roomId) {
        return roomService.deleteRoomById(roomId);
    }

    @DeleteMapping("/deleteRoomAll")
    public String deleteRoomAll() {
        return roomService.deleteRoomAll();
    }


}
