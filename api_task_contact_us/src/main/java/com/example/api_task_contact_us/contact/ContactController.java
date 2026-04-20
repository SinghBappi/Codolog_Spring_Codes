package com.example.api_task_contact_us.contact;

//package com.example.demo.contact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    // 1. Initialize Logger
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    private final ContactRepository contactRepository;

    // Constructor Injection
    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    // ==========================================
    // 1. GET API - Fetch all messages
    // ==========================================
    @GetMapping("/all")
    public List<ContactMessage> getAllMessages() {
        logger.info("GET request received at /api/contact/all");

        try {
            List<ContactMessage> messages = contactRepository.findAll();
            logger.info("Successfully retrieved {} messages from the database.", messages.size());
            return messages;
        } catch (Exception e) {
            logger.error("Failed to retrieve messages from database. Error: {}", e.getMessage(), e);
            throw new RuntimeException("Could not fetch messages", e);
        }
    }

    // ==========================================
    // 2. POST API - Submit a new message
    // ==========================================
    @PostMapping("/submit")
    public ContactMessage submitContactMessage(@RequestBody ContactMessage newMessage) {
        logger.info("POST request received at /api/contact/submit");

        // Log incoming data (excluding the long message body for terminal cleanliness)
        logger.info("Processing submission for Name: [{}], Email: [{}], Contact: [{}]",
                newMessage.getFullname(), newMessage.getEmail(), newMessage.getContactno());

        // Step 1: Pre-validation checks for logging purposes
        if (newMessage.getSubject() != null && newMessage.getSubject().length() > 250) {
            logger.warn("Validation Warning: Subject length ({}) exceeds 250 characters!", newMessage.getSubject().length());
        }

        if (newMessage.getMessage() != null && newMessage.getMessage().length() > 1000) {
            logger.warn("Validation Warning: Message length ({}) exceeds 1000 characters!", newMessage.getMessage().length());
        }

        // Step 2: Attempt to save to database
        try {
            logger.debug("Attempting to execute JPA save() method...");
            ContactMessage savedMessage = contactRepository.save(newMessage);

            logger.info("SUCCESS: Contact message saved successfully! Assigned Database ID: {}", savedMessage.getId());
            return savedMessage;

        } catch (Exception e) {
            // Step 3: Handle errors (like duplicate emails or oversized strings)
            logger.error("DATABASE ERROR: Failed to save the contact message. Cause: {}", e.getMessage());
            throw new RuntimeException("Failed to submit contact message. Please ensure the email is unique and character limits are respected.", e);
        }
    }
}