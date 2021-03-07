package com.example.shardingspherejdbc.service;

import com.example.shardingspherejdbc.mapper.StudentMapper;
import com.example.shardingspherejdbc.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentMapper studentMapper;

    public void add(){
        studentMapper.add();
    }

    public Student getStudent(){
        Student student = studentMapper.getStudent();
        return student;
    }
}
