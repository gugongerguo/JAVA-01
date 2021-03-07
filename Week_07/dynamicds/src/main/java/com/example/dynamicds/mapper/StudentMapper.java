package com.example.dynamicds.mapper;

import com.example.dynamicds.model.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudentMapper {

    @Insert("insert into t_student values (1,'123',12)")
    void add();

    @Select("select * from t_student where id =1")
    @Results({
            @Result(column="id", property="id"),
            @Result(column="name", property="name"),
            @Result(column = "age", property = "age")
    })
    Student getStudent();
}
