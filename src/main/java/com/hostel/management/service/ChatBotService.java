package com.hostel.management.service;

import org.springframework.stereotype.Service;

@Service
public class ChatBotService {

    public String getResponse(String userInput) {
        userInput = userInput.toLowerCase();

        // --- Greeting ---
        if (userInput.contains("hi") || userInput.contains("hello") || userInput.contains("hey")) {
            return "Hi there! 👋 I'm your Hostel Assistant. You can ask me about rooms, fees, or hostel rules.";
        }

        // --- Room Related ---
        else if (userInput.contains("room") && (userInput.contains("available") || userInput.contains("availability"))) {
            return "You can check available rooms under the 'Room Availability' section in your student dashboard.";
        } 
        else if (userInput.contains("room") && userInput.contains("allocation")) {
            return "Your room allocation details are shown in the 'My Room Details' section.";
        } 
        else if (userInput.contains("change room") || userInput.contains("room change request")) {
            return "To request a room change, go to 'Requests' → 'Room Change Request'.";
        }

        // --- Fee Related ---
        else if (userInput.contains("fee") || userInput.contains("payment")) {
            return "You can view your fee details and make payments under the 'Fee Management' tab.";
        } 
        else if (userInput.contains("due") || userInput.contains("balance")) {
            return "Check your due fees under 'My Fees'. Make sure all payments are cleared before the due date!";
        }

        // --- Hostel Rules / Timings ---
        else if (userInput.contains("rules") || userInput.contains("timing") || userInput.contains("curfew")) {
            return "Hostel timings are from 6:00 AM to 10:00 PM. Please follow the rules mentioned in your dashboard notice section.";
        }

        // --- Contact / Support ---
        else if (userInput.contains("warden") || userInput.contains("contact") || userInput.contains("helpdesk")) {
            return "You can contact the hostel warden at hostel.warden@gmail.com or visit the warden office during office hours.";
        } 
        else if (userInput.contains("complaint") || userInput.contains("issue") || userInput.contains("problem")) {
            return "To raise a complaint, go to 'Student Dashboard' → 'Raise Complaint' section.";
        }

        // --- General Help ---
        else if (userInput.contains("what can you do") || userInput.contains("help")) {
            return "I can help you with hostel-related questions like:\n- Allocated room details \n- Fee details\n- Contact info\n- Rules and timings\n- How to request or raise issues 😊";
        }

        // --- Default response ---
        else {
            return "I'm not sure about that. Please try asking about room details, fees, or hostel rules 😊";
        }
    }
}


