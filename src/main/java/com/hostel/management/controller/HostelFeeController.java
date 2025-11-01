package com.hostel.management.controller;

import com.hostel.management.entity.HostelFee;
import com.hostel.management.repository.HostelFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/fee")
public class HostelFeeController {

    @Autowired
    private HostelFeeRepository feeRepository;

    // Show fee form and current fees
    @GetMapping("/ui")
    public String feePage(Model model) {
        model.addAttribute("fees", feeRepository.findAll());
        return "hostel-fee"; // maps to hostel-fee.html
    }

    // Add or update yearly fee
    @PostMapping("/set")
    public String setFee(@RequestParam int year, @RequestParam double feeAmount) {
        HostelFee existing = feeRepository.findByYear(year);
        if (existing != null) {
            existing.setFeeAmount(feeAmount);
            feeRepository.save(existing);
        } else {
            feeRepository.save(new HostelFee(year, feeAmount));
        }
        return "redirect:/admin/fee/ui";
    }
}
