package com.example.shardingspherejdbc.mapper;

import com.example.shardingspherejdbc.model.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudentMapper {

    @Insert("insert into t_student values (2,'123',12)")
    void add();

    @Select("select * from t_student where id =2")
    @Results({
            @Result(column="id", property="id"),
            @Result(column="name", property="name"),
            @Result(column = "age", property = "age")
    })
    Student getStudent();
}
