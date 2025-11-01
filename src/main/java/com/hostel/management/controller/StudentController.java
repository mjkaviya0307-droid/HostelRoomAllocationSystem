package com.hostel.management.controller;

import com.hostel.management.entity.Student;
import com.hostel.management.entity.Room;
import com.hostel.management.repository.StudentRepository;
import com.hostel.management.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

/**
 * REST API Controller for Student Management
 */
@RestController
@RequestMapping("/api/students") // 👈 all API calls start with /api/students
class StudentApiController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoomRepository roomRepository;

    // Add a new student
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // Get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get a student by id
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id).orElse(null);
    }

 // Delete a student by id and update room's allocated count
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        // Find the student
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        // If the student is assigned to a room, decrement its allocatedCount
        Room room = student.getRoom();
        if (room != null) {
            room.setAllocatedCount(room.getAllocatedCount() - 1);
            roomRepository.save(room);
        }

        // Delete the student
        studentRepository.delete(student);

        return "Student with id " + id + " deleted and room count updated.";
    }


    // Assign a student to a specific room
    @PostMapping("/assign/{roomId}")
    public Student assignStudentToRoom(@PathVariable Long roomId, @RequestBody Student student) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + roomId));

        // Capacity check
        if (room.getAllocatedCount() >= room.getCapacity()) {
            throw new RuntimeException("Room is already full. Cannot add more students.");
        }

        // Link student to room
        student.setRoom(room);

        // Increase allocated count
        room.setAllocatedCount(room.getAllocatedCount() + 1);
        roomRepository.save(room);

        // Save student
        return studentRepository.save(student);
    }

    // Update a student by id
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        return studentRepository.findById(id).map(student -> {
            student.setName(updatedStudent.getName());
            student.setEmail(updatedStudent.getEmail());
            student.setDepartment(updatedStudent.getDepartment());
            student.setYear(updatedStudent.getYear());
            return studentRepository.save(student);
        }).orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    // Unassign a student from a room
    @PutMapping("/{id}/unassign")
    public Student unassignStudentFromRoom(@PathVariable Long id) {
        return studentRepository.findById(id).map(student -> {
            student.setRoom(null); // remove room assignment
            return studentRepository.save(student);
        }).orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }
}

/**
 * UI Controller for Thymeleaf Pages
 */
@Controller
@RequestMapping("/students")
class StudentUIController {

    @Autowired
    private StudentRepository studentRepository;

    // Show students in Thymeleaf template
    @GetMapping("/ui")
    public String studentsPage(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "students"; // maps to templates/students.html
    }
}

