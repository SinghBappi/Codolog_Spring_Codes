package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Hi {
    @GetMapping("/api")
//    String xyz(){
//        return "Hey, I am a Software Intern at Codolog !";
//    }
    List<DTO> xyz(){
//        return "Hey, I am a Software Intern at Codolog !";
        List<DTO> mod_apk = new ArrayList<>();
        DTO ds = new DTO();
        ds.setId(25354);
        ds.setIsactive("TRUE");
        ds.setStart_time("22-02-2026");
        ds.setEnd_time("24-02-2026");
//        ds.setEnd_time();
        mod_apk.add(ds);
        return mod_apk;
    }

}
