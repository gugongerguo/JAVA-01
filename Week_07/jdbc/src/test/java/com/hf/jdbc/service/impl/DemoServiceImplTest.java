package com.hf.jdbc.service.impl;

import com.hf.jdbc.entity.Demo;
import com.hf.jdbc.entity.Student;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoServiceImplTest {

    @Autowired
    DemoServiceImpl demoService;

    @Test
    void batchAddDemo() {
        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= 1; i++) {
            List<Demo> demos = new ArrayList<Demo>();
            for (int j = 1; j <= 1000000; j++) {
                demos.add(new Demo(1, 1, "这是一个测试数据", 345l, new Timestamp(System.currentTimeMillis()), true, "test-test-test-over"));
            }
            demoService.batchAddDemo(demos.toArray(new Demo[demos.size()]));

        }
        long endTime = System.currentTimeMillis();
        long s = (endTime - startTime) / 1000;
        System.out.println("运行时间：" + (endTime - startTime) + "ms" + "-->" + s / 60 + "m" + s % 60 + "s");
        //7个表字段，各种数据类型，打开开关rewriteBatchedStatements=true
        //batch insert 1000条*1000次 运行时间：46852ms-->0m46s
        //batch insert 10000条*100次 运行时间：14708ms-->0m14s
        //batch insert 100000条*10次 运行时间：11840ms-->0m11s
        //batch insert 1000000条*1次 运行时间：14201ms-->0m14s
    }

    @Test
    void batchAddDemo1() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int threadNum = 1;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        for (int i = 1; i <= threadNum; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 1; j <= 10 / threadNum; j++) {
                    List<Demo> demos = new ArrayList<Demo>();
                    for (int n = 1; n <= 100000; n++) {
                        demos.add(new Demo(1, 1, "这是一个测试数据", 345l, new Timestamp(System.currentTimeMillis()), true, "test-test-test-over"));
                    }
                    demoService.batchAddDemo(demos.toArray(new Demo[demos.size()]));
                }
                countDownLatch.countDown();
            });
            thread.start();
        }
        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        long s = (endTime - startTime) / 1000;
        System.out.println("运行时间：" + (endTime - startTime) + "ms" + "-->" + s / 60 + "m" + s % 60 + "s");
        //4核16G MacBookPro 多线程测试，7个表字段，各种数据类型，打开开关rewriteBatchedStatements=true
        //选取batch insert 100000条/次
        //1线程  运行时间：12171ms-->0m12s
        //2线程  运行时间：7791ms-->0m7s
        //5线程  运行时间：5061ms-->0m5s
        //10线程 运行时间：7014ms-->0m7s
    }
}