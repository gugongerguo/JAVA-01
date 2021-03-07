package com.example.dynamicds.service;

import com.example.dynamicds.annotation.DataSource;
import com.example.dynamicds.mapper.StudentMapper;
import com.example.dynamicds.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentMapper studentMapper;

    @DataSource("primary")
    public void add(){
        studentMapper.add();
    }

    @DataSource("replica")
    public Student getStudent(){
        Student student = studentMapper.getStudent();
        return student;
    }
}
