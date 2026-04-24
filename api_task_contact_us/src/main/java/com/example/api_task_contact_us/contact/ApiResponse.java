package com.example.api_task_contact_us.contact;

import java.time.LocalDateTime;

public class ApiResponse {

    private int status;
    private String message;
    private Object data;
    private LocalDateTime timestamp;

    // 1. The Constructor MUST use the 'this' keyword to save the incoming data
    public ApiResponse(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now(); // Automatically captures the exact moment of response
    }

    // 2. Getters MUST be present and correctly return the variables so Spring Boot can read them
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}