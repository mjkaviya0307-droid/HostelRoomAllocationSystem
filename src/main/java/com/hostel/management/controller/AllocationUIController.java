package com.hostel.management.controller;

import com.hostel.management.repository.RoomRepository;
import com.hostel.management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/allocation")
public class AllocationUIController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoomRepository roomRepository;

    // Allocation page (pastel green theme later)
    @GetMapping("/ui")
    public String allocationPage(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("rooms", roomRepository.findAll());
        return "allocation"; // maps to templates/allocation.html
    }
}