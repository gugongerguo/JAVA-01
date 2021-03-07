package com.example.dynamicds.service;

import com.example.dynamicds.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class StudentServiceTest {

    @Autowired
    StudentService studentService;

    @Test
    void add() {
        studentService.add();
    }

    @Test
    void getStudent() {
        Student student = studentService.getStudent();
        System.out.println(student.toString());
    }
}