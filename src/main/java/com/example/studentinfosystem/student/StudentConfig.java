package com.example.studentinfosystem.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student zach = new Student(
                  1L,
                  "Zach",
                  "ZachRichDev@gmail.com",
                  LocalDate.of(1999, Month.JANUARY, 19)
            );

            Student chad = new Student(
                    "chad",
                    "chad@gmail.com",
                    LocalDate.of(2004, Month.JANUARY, 20)
                    );
            repository.saveAll(
                    List.of(zach, chad)
            );
        };
    }

}
