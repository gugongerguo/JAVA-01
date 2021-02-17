package com.hf.starterdemo.spring02;

import com.hf.starterdemo.spring01.Student;
import lombok.Data;

import java.util.List;

@Data
public class Klass { 
    
    List<Student> students;
    
    public void dong(){
        System.out.println("dong---get students====>"+this.getStudents());
    }
    
}
