package com.example.studentinfosystem;

import com.example.studentinfosystem.student.Student;
import com.example.studentinfosystem.student.StudentService;
import org.mockito.Mock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
@RestController
public class StudentInfoSystemApplication {

    @Mock
    private StudentService studentService;

    public static void main(String[] args) {

        SpringApplication.run(StudentInfoSystemApplication.class, args);

    }

}
