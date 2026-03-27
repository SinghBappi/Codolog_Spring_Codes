package com.example.demo.courses;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/studentsfetch")
public class StudentController {
    private final StudentRepository studentRepo;
    public StudentController(StudentRepository studentRepo){

        this.studentRepo=studentRepo;
    }
    @GetMapping
    public List<student> getAllStudents(){
        return studentRepo.findAll();
    }

    @PostMapping
    public student createStatement(@RequestBody student student){
        return studentRepo.save(student);
    }
}
