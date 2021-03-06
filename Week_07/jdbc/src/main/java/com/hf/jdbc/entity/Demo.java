package com.hf.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Demo {

    private int id;

    private int label1;

    private String label2;

    private long label3;

    private Timestamp label4;

    private boolean label5;

    private String label6;
}
