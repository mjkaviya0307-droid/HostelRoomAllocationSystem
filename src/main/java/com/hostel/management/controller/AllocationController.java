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

    /**
     * Allocates a student to a specific room based on:
     * - Room capacity
     * - Student's existing allocation
     * - Gender-based room policy
     */
    @PostMapping("/{studentId}/room/{roomId}")
    public String allocateRoom(@PathVariable Long studentId, @PathVariable Long roomId) {
        // Fetch the student and room entities from the database
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + roomId));

        // ✅ Check if student already has a room
        if (student.getRoom() != null) {
            return "❗ Student " + student.getName() + " is already allocated to Room "
                    + student.getRoom().getRoomNumber();
        }

        // ✅ Check if the room is full
        if (room.getAllocatedCount() >= room.getCapacity()) {
            return "🚫 Room " + room.getRoomNumber() + " is already full!";
        }

        // ✅ Gender-based allocation check
        if (!room.getStudents().isEmpty()) {
            // Take the gender of the first student already in this room
            String existingGender = room.getStudents().get(0).getGender();

            if (existingGender != null && !existingGender.equalsIgnoreCase(student.getGender())) {
                return "⚠️ Room " + room.getRoomNumber() + " is for "
                        + existingGender + " students only!";
            }
        }

        // ✅ Proceed with allocation
        student.setRoom(room);
        room.setAllocatedCount(room.getAllocatedCount() + 1);

        studentRepository.save(student);
        roomRepository.save(room);

        return "✅ Student " + student.getName() + " (" + student.getGender()
                + ") allocated to Room " + room.getRoomNumber();
    }
}
