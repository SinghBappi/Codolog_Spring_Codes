package com.example.api_task_contact_us.contact;


import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

@Entity
@Table(name = "contact_us")
public class ContactMessage {

    // 1. Logger (Marked as Transient so the DB ignores it)
    @Transient
    private static final Logger logger = LoggerFactory.getLogger(ContactMessage.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullname;

    @Column(nullable = false, unique = true)
    private String email;

    // Numeric field for contact number
    @Column(name = "contactno")
    private Long contactno;

    // Strict limit of 250 characters
    @Column(length = 250)
    private String subject;

    // Strict limit of 1000 characters
    @Column(length = 1000)
    private String message;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    // Lifecycle hook to log and set time right before saving to DB
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        logger.debug("Database Event: Preparing to save contact message from email '{}' at {}", this.email, this.createdAt);
    }

    // --- Core Java Getters and Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Long getContactno() { return contactno; }
    public void setContactno(Long contactno) { this.contactno = contactno; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
