package com.hostel.management.controller;

import com.hostel.management.repository.RoomRepository;
import com.hostel.management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoomRepository roomRepository;

    // Show login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "admin-login";
    }

    // Handle login post request
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        // Basic check (you can replace with real auth later)
        if ("admin".equals(username) && "password".equals(password)) {
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "admin-login";
        }
    }

    // Dashboard page
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        long totalStudents = studentRepository.count();
        long totalRooms = roomRepository.count();

        long allocatedStudents = studentRepository.findAll().stream()
                .filter(s -> s.getRoom() != null)
                .count();

        long availableRooms = roomRepository.findAll().stream()
                .filter(r -> r.getAllocatedCount() < r.getCapacity())
                .count();

        List<Map<String, Object>> cards = new ArrayList<>();

        cards.add(Map.of("title", "Total Students", "value", totalStudents));
        cards.add(Map.of("title", "Total Rooms", "value", totalRooms));
        cards.add(Map.of("title", "Allocated Students", "value", allocatedStudents));
        cards.add(Map.of("title", "Available Rooms", "value", availableRooms));

        model.addAttribute("cards", cards);
        return "admin-dashboard";
    }
}
