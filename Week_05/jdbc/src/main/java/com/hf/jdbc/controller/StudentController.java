package com.hf.jdbc.controller;

import com.hf.jdbc.entity.Student;
import com.hf.jdbc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Student getStuById(@PathVariable int id){
        Student student = studentService.queryById(id);
        return student;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public int addStu(Student student){
        int res = studentService.addStudent(student);
        return res;
    }

    @RequestMapping(value = "/batch", method = RequestMethod.POST)
    public String BatchAddStu(Student[] students){
        String res = studentService.batchAddStudent(students);
        return res;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public int deleteStu(@PathVariable int id){
        System.out.println(id);
        int res = studentService.deleteStudent(id);
        return res;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public int updateStu(Student student){
        int res = studentService.updateStudent(student);
        return res;
    }
}
