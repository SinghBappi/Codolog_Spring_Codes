package com.example.api_task_contact_us.contact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    // ==========================================
    // 1. GET API
    // ==========================================
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllMessages() {
        logger.info("GET request received at /api/contact/all");

        try {
            List<ContactMessage> messages = contactRepository.findAll();
            logger.info("Successfully retrieved {} messages.", messages.size());

            // Keep the data here so the admin can see the list of messages!
            ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Messages fetched successfully", messages);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("Failed to retrieve messages. Error: {}", e.getMessage());
            ApiResponse errorResponse = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to fetch messages", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // ==========================================
    // 2. POST API
    // ==========================================
    @PostMapping("/submit")
    public ResponseEntity<ApiResponse> submitContactMessage(@RequestBody ContactMessage newMessage) {
        logger.info("POST request received at /api/contact/submit");
        logger.info("Processing submission for Email: [{}]", newMessage.getEmail());

        if (newMessage.getSubject() != null && newMessage.getSubject().length() > 250) {
            logger.warn("Validation Warning: Subject exceeds 250 characters!");
            ApiResponse badResponse = new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Subject cannot exceed 250 characters.", null);
            return ResponseEntity.badRequest().body(badResponse);
        }

        try {
            ContactMessage savedMessage = contactRepository.save(newMessage);
            logger.info("SUCCESS: Contact message saved. ID: {}", savedMessage.getId());

            // UPDATED: Developer-friendly message and data set explicitly to null!
            ApiResponse successResponse = new ApiResponse(HttpStatus.CREATED.value(), "Data inserted successfully!", null);
            return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);

        } catch (Exception e) {
            logger.error("DATABASE ERROR: Failed to save message. Cause: {}", e.getMessage());
            ApiResponse errorResponse = new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Failed to insert data. Ensure unique constraints are met.", null);
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}