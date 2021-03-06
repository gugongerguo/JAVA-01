package com.hf.jdbc.service.impl;

import com.hf.jdbc.entity.Demo;
import com.hf.jdbc.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class DemoServiceImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(rollbackFor = Exception.class)
    public String batchAddDemo(Demo[] demos) {
//        DataSource dataSource = jdbcTemplate.getDataSource();
//        Connection connection = dataSource.getConnection();
//        DatabaseMetaData metaData = connection.getMetaData();
//        System.out.println("数据源>>>>>>" + dataSource.getClass());
//        System.out.println("连接>>>>>>>>" + connection);
//        System.out.println("连接地址>>>>" + connection.getMetaData().getURL());
//        System.out.println("驱动名称>>>>" + metaData.getDriverName());
//        System.out.println("驱动版本>>>>" + metaData.getDriverVersion());
//        System.out.println("数据库名称>>" + metaData.getDatabaseProductName());
//        System.out.println("数据库版本>>" + metaData.getDatabaseProductVersion());
//        System.out.println("连接用户名称>" + metaData.getUserName());
        String sql = "insert into t_insert_demo(label1,label2,label3,label4,label5,label6) values(?,?,?,?,?,?)";
        int[] ints = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, demos[i].getLabel1());
                ps.setString(2, demos[i].getLabel2());
                ps.setLong(3, demos[i].getLabel3());
                ps.setTimestamp(4, demos[i].getLabel4());
                ps.setBoolean(5, demos[i].isLabel5());
                ps.setString(6, demos[i].getLabel6());
            }

            @Override
            public int getBatchSize() {
                return demos.length;
            }
        });
        return ints.toString();
//        return null;
    }
}
