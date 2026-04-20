package com.example.api_task_contact_us.contact;

//package com.example.demo.contact;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//package com.example.demo.contact;


@Repository
public interface ContactRepository extends JpaRepository<ContactMessage, Long> {
    // Spring Boot provides save(), findAll(), findById() automatically here!
}