package com.example.shardingspherejdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.shardingspherejdbc.mapper")
public class ShardingspherejdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingspherejdbcApplication.class, args);
    }

}
