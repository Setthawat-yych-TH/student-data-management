package com.assignment.studentdatamanagement.repository;

import com.assignment.studentdatamanagement.dto.Grade;
import com.assignment.studentdatamanagement.dto.Room;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    public List<Room> findByRoomNumberAndGrade (Integer roomNumber, Grade grade);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "ALTER SEQUENCE room_id_seq RESTART WITH 1")
    public int resetRoomId();

 }
