package com.hostel.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hostel.management.entity.Room;
import com.hostel.management.entity.Student;
import com.hostel.management.repository.RoomRepository;
import com.hostel.management.repository.StudentRepository;

@RestController
@RequestMapping("/api/allocate")
public class AllocationController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoomRepository roomRepository;

    // Allocate a student to a room
    @PostMapping("/{studentId}/room/{roomId}")
    public String allocateRoom(@PathVariable Long studentId, @PathVariable Long roomId) {
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new
        		RuntimeException("Student not found with ID:"+studentId));
        Room room = roomRepository.findById(roomId).orElseThrow(()-> new
        		RuntimeException("Room not found with ID:"+roomId));

        //Check if student already has a room
        if (student.getRoom() != null) {
            return "Student " + student.getName() + " is already allocated to a room!"+student.getRoom().getRoomNumber();
        }
        // Check if room is full
        if (room.getAllocatedCount() >= room.getCapacity()) {
            return "Room is already full!";
        }

        // Allocate room
        student.setRoom(room);
        room.setAllocatedCount(room.getAllocatedCount() + 1);

        studentRepository.save(student);
        roomRepository.save(room);

        return "Student " + student.getName() + " allocated to Room " + room.getRoomNumber();
    }
}