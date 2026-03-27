package com.example.demo.courses;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="student")

public class student {
    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column(nullable = false)
    private String studentName;


    @Column(nullable = false,unique = true)
    private String email;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.time.LocalDateTime createDate;

    // Getter and setters ki barish
    //id
    public Long getStudentId(){return studentId;}
    public void  setStudentId(Long studentId){this.studentId=studentId;}


    //name
    public String getStudentName(){return studentName;}
    public void setStudentName(String studentName){this.studentName=studentName;}

    //email

    public String getEmail(){return email;}
    public void setEmail(String email){ this.email=email;}

    //time

    public java.time.LocalDateTime getCreateDate(){return createDate;}
    public void setCreateDate(java.time.LocalDateTime createDate){this.createDate=createDate;}










}
