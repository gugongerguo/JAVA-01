package com.hf.jdbc.service.impl;

import com.hf.jdbc.entity.Student;
import com.hf.jdbc.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class StudentServiceImplTest {

    @Autowired
    StudentService studentService;

    @Test
    void addStudent() {
        long startTime = System.currentTimeMillis();
        for (int i = 1; i < 1000001; i++) {
            studentService.addStudent(new Student(i, "a", 11));
        }
        long endTime = System.currentTimeMillis();
        long s = (endTime - startTime) / 1000;
        System.out.println("运行时间：" + (endTime - startTime) + "ms" + "-->" + s / 60 + "m" + s % 60 + "s");
        //单条insert太太太慢，遥遥无期，放弃。
    }

    @Test
    void batchAddStudent() {
        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= 1; i++) {
            List<Student> students = new ArrayList<Student>();
            for (int j = 1; j <= 1000000; j++) {
                students.add(new Student(j, "a", 11));
            }
            studentService.batchAddStudent(students.toArray(new Student[students.size()]));

        }
        long endTime = System.currentTimeMillis();
        long s = (endTime - startTime) / 1000;
        System.out.println("运行时间：" + (endTime - startTime) + "ms" + "-->" + s / 60 + "m" + s % 60 + "s");
        //两字段
        //batch insert 1000条*1000次 运行时间：148532ms-->2m28s
        //batch insert 10000条*100次 运行时间：115859ms-->1m55s
        //batch insert 100000条*10次 运行时间：110460ms-->1m50s
        //加上参数后jdbc:mysql://127.0.0.1:3316/insert_demo?rewriteBatchedStatements=true
        //batch insert 1000条*1000次 运行时间：35963ms-->0m35s
        //batch insert 10000条*100次 运行时间：7679ms-->0m7s
        //batch insert 100000条*10次 运行时间：4699ms-->0m4s
        //batch insert 1000000条*1次 运行时间：4954ms-->0m4s
    }
}