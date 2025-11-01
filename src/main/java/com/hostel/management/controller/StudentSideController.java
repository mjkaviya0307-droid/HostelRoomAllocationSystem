package com.hostel.management.controller;

import com.hostel.management.entity.Student;
import com.hostel.management.entity.HostelFee;
import com.hostel.management.repository.StudentRepository;
import com.hostel.management.repository.HostelFeeRepository;
import com.hostel.management.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentSideController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired(required = false)
    private HostelFeeRepository hostelFeeRepository;

    @Autowired
    private EmailService emailService;

    // Login page
    @GetMapping("/login")
    public String loginPage() {
        return "student-login";
    }

    // Handle login
    @PostMapping("/login")
    public String handleLogin(@RequestParam Long id, @RequestParam String password, Model model) {

        Optional<Student> studentOpt = studentRepository.findById(id);
        if (studentOpt.isEmpty()) {
            model.addAttribute("error", "Invalid student ID");
            return "student-login";
        }

        Student student = studentOpt.get();

        // Check password (plain text for testing)
        if (!password.equals(student.getPassword())) {
            model.addAttribute("error", "Invalid password");
            return "student-login";
        }

        model.addAttribute("student", student);

        // Fetch latest hostel fee safely
        Double feeAmount = null;
        HostelFee latestFee = hostelFeeRepository.findLatestFee();
        if (latestFee != null) {
            feeAmount = latestFee.getFeeAmount();
        }
        model.addAttribute("feeAmount", feeAmount);

        // ✅ Send email after successful login
        emailService.sendEmail(
            student.getEmail(),
            "Login Successful - Hostel Management System",
            "Hello " + student.getName() + ",\n\nYou have successfully logged in to your student dashboard.\n\nBest,\nHostel Admin"
        );

        return "student-dashboard";
    }

    // Payment page
    @GetMapping("/payment")
    public String studentPaymentPage(@RequestParam Long id, Model model) {
        Optional<Student> studentOpt = studentRepository.findById(id);
        if (studentOpt.isEmpty()) {
            return "redirect:/student/login";
        }

        Student student = studentOpt.get();
        model.addAttribute("student", student);

        Double feeAmount = null;
        HostelFee latestFee = hostelFeeRepository.findLatestFee();
        if (latestFee != null) {
            feeAmount = latestFee.getFeeAmount();
        }
        model.addAttribute("feeAmount", feeAmount);

        return "student-payment";
    }

    // Go back to dashboard
    @GetMapping("/dashboard")
    public String showDashboard(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            return "redirect:/student/login";
        }

        Optional<Student> studentOpt = studentRepository.findById(id);
        if (studentOpt.isEmpty()) {
            return "redirect:/student/login";
        }

        Student student = studentOpt.get();
        model.addAttribute("student", student);

        // Fetch latest hostel fee
        Double feeAmount = null;
        HostelFee latestFee = hostelFeeRepository.findLatestFee();
        if (latestFee != null) {
            feeAmount = latestFee.getFeeAmount();
        }
        model.addAttribute("feeAmount", feeAmount);

        return "student-dashboard";
    }
}

