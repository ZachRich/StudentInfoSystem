package com.example.studentinfosystem.student;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import org.junit.*;
import org.mockito.BDDMockito.*;
import org.hamcrest.*;

import static org.junit.Assert.*;

public class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @Test
    public void itShouldfindStudentByEmail() {

        //Given
        Student student = new Student(
                1L,
                "Zach",
                "ZachRichDev@gmail.com",
                LocalDate.of(1999, Month.JANUARY, 19)
        );


        //When
        boolean expected = underTest.findByEmail(student.getEmail());

        //Then
        //assertEquals(student.getEmail(), expected.get().getEmail());

    }
}