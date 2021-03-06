package com.hf.jdbc.service;

import com.hf.jdbc.entity.Student;

public interface StudentService {

    Student queryById(int id);

    int addStudent(Student student);

    String batchAddStudent(Student[] students);

    int deleteStudent(int id);

    int updateStudent(Student student);

}
