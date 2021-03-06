package com.hf.jdbc.service.impl;

import com.alibaba.druid.support.json.JSONParser;
import com.hf.jdbc.entity.Student;
import com.hf.jdbc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Student queryById(int id) {
        String sql = "select * from student where id = ?";
        List<Student> students = jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<Student>(Student.class));
        Student student = null;
        if (!students.isEmpty()) {
            student = students.get(0);
        }
        return student;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addStudent(Student student) {
        String sql = "insert into student(name,age) values(?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int resRow = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql,new String[]{"id"});
                ps.setString(1,student.getName());
                ps.setInt(2,student.getAge());
                return ps;
            }
        },keyHolder);
//        System.out.println("操作记录数："+resRow+" 主键："+keyHolder.getKey());
        return keyHolder.getKey().intValue();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String batchAddStudent(Student[] students) {
        String sql = "insert into student(name,age) values(?,?)";
        int[] ints = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, students[i].getName());
                ps.setInt(2, students[i].getAge());
            }

            @Override
            public int getBatchSize() {
                return students.length;
            }
        });
        return ints.toString();
    }

    @Override
    public int deleteStudent(int id) {
        String sql ="delete from student where id = ?";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public int updateStudent(Student student) {
        String sql = "update student set name=?,age=? where id=?";
        int res = jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1,student.getName());
                preparedStatement.setInt(2,student.getAge());
                preparedStatement.setInt(3,student.getId());
            }
        });
        return res;
    }
}
