package com.hostel.management.controller;

import com.hostel.management.service.ChatBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chatbot")
@CrossOrigin("*")
public class ChatBotController {

    @Autowired
    private ChatBotService chatBotService;

    // ✅ Handles chatbot messages
    @PostMapping("/ask")
    public String getChatbotResponse(@RequestBody String message) {
        return chatBotService.getResponse(message);
    }
}


