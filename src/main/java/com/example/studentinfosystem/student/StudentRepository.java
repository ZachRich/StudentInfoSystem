package com.example.studentinfosystem.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    //Data Access Layer


    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    boolean findByEmail(String email);


}
