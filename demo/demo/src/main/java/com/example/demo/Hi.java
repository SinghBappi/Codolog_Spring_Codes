package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hi {
    @GetMapping("/api")
    String xyz(){
        return "Hey, I am a Software Intern at Codolog !";
    }

}
