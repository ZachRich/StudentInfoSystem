package com.example.studentinfosystem.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.*;
import org.springframework.web.client.HttpClientErrorException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    private StudentService mockStudentService;

    @BeforeEach
    void setUp() {

        mockStudentService = new StudentService(studentRepository);
    }

    @Test
    void canGetAllStudents() {

    //Given
        mockStudentService.getStudents();
    //Then
        verify(studentRepository).findAll();

    }

    @Test
    void canAddNewStudent() {

        //Given
        Student student = new Student(
                "Zach",
                "ZachRichDev@gmail.com",
                LocalDate.of(2021, Month.APRIL, 20)
        );

        //when
        mockStudentService.addNewStudent(student);

        //then
        ArgumentCaptor<Student> studentArgumentCaptor =
                ArgumentCaptor.forClass(Student.class);
        //Capture the student we pass to save method
        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student studentCaptorValue = studentArgumentCaptor.getValue();

        assertEquals(student, studentCaptorValue);
    }

    @Test
    void willThrowWhenEmailIsTaken() {

        //Given
        Student student = new Student(
                "Zach",
                "ZachRichDev@gmail.com",
                LocalDate.of(2021, Month.APRIL, 20)
        );

        //when
        given(studentRepository.findByEmail(student.getEmail())).willReturn(true);
        //then
        assertThatThrownBy(() -> mockStudentService.addNewStudent(student))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Email " + student.getEmail() +  " has Been Taken");

        verify(studentRepository, never()).save(any());

    }

    @Test
    void deleteStudent() {

        //Given
        Student student = new Student(
                1L,
                "Zach",
                "ZachRichDev@gmail.com",
                LocalDate.of(2021, Month.APRIL, 20)
        );
        given(studentRepository.existsById(student.getId())).willReturn(true);

        //When
        mockStudentService.deleteStudent(student.getId());

        //Then
        ArgumentCaptor<Student> studentArgumentCaptor =
                ArgumentCaptor.forClass(Student.class);

        verify(mockStudentService).deleteStudent(student.getId());

        Student studentArgumentCaptorValue = studentArgumentCaptor.getValue();

        assertEquals(student, studentArgumentCaptorValue);

    }

    @Test
    @Disabled
    void updateStudent() {
    }
}