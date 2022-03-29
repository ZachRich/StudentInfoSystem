package com.example.studentinfosystem.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    //Service Layer (Business Logic)

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {

        boolean studentOptional = studentRepository.findByEmail(student.getEmail());

        if(studentOptional == true) {
            throw new IllegalStateException("Email " + student.getEmail() +  " has Been Taken");
        }

        studentRepository.save(student);

    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists) {
            throw new IllegalStateException("Student with id: " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with Id" + studentId + " does not exist"));

        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            boolean studentOptional = studentRepository.findByEmail(email);
            if(studentOptional == true) {
                throw new IllegalStateException("Email " + student.getEmail() +  " has Been Taken");
            }
            student.setEmail(email);
        }


    }
}
