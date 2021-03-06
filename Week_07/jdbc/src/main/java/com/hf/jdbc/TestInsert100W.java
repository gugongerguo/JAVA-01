package com.hf.jdbc;

import com.hf.jdbc.entity.Student;
import com.hf.jdbc.service.StudentService;
import com.hf.jdbc.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class TestInsert100W {

    public static void main(String[] args) {
        StudentServiceImpl studentService = new StudentServiceImpl();
        long startTime = System.currentTimeMillis();
        for(int i=1;i<11;i++){
        studentService.addStudent(new Student(i,"a",11));
        }
        long endTime = System.currentTimeMillis();
        long s = (startTime-endTime)/1000;
        System.out.println("运行时间："+(startTime-endTime)+"ms"+"-->"+s/60+"m"+s%60+"s");
    }
}
