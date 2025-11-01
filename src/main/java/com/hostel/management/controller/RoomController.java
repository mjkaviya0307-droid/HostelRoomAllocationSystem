package com.hostel.management.controller;

import com.hostel.management.entity.Room;
import com.hostel.management.entity.Student;
import com.hostel.management.repository.RoomRepository;
import com.hostel.management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;

/**
 * REST API Controller for Room Management
 */
@RestController
@RequestMapping("/api/rooms")
class RoomApiController {

    @Autowired
    private RoomRepository roomRepository;

    // --- API endpoints ---
    @PostMapping
    public Room addRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Long id) {
        roomRepository.deleteById(id);
        return "Room with id " + id + " deleted.";
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Long id, @RequestBody Room updatedRoom) {
        return roomRepository.findById(id).map(room -> {
            room.setRoomNumber(updatedRoom.getRoomNumber());
            room.setCapacity(updatedRoom.getCapacity());
            room.setAllocatedCount(updatedRoom.getAllocatedCount());
            return roomRepository.save(room);
        }).orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
    }
}

/**
 * UI Controller for Thymeleaf Pages
 */
@Controller
@RequestMapping("/rooms")
class RoomUIController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Show all rooms
    @GetMapping("/ui")
    public String roomsPage(Model model) {
        List<Room> rooms = roomRepository.findAll();

        // Dynamically calculate how many students are allocated to each room
        rooms.forEach(room -> {
            long count = studentRepository.countByRoomId(room.getId());
            room.setAllocatedCount((int) count); // temporarily set for display
        });

        model.addAttribute("rooms", rooms);
        return "rooms"; // refers to rooms.html
    }


    // View details for a specific room
    @GetMapping("/{id}")
    public String viewRoomDetails(@PathVariable("id") Long id, Model model) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid room ID: " + id));
        List<Student> allocatedStudents = studentRepository.findByRoomId(id);

        model.addAttribute("room", room);
        model.addAttribute("allocatedStudents", allocatedStudents);
        return "room-details";
    }
}