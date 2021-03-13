# 学习笔记

1. （必做）设计对前面的订单表数据进行水平分库分表，拆分2个库，每个库16张表。并在新结构在演示常见的增删改查操作。代码、sql 和配置文件，上传到 Github。

   - 作业路径：https://github.com/gugongerguo/JAVA-01/tree/main/Week_08/multdatabasestables
   
- 作业分析：
  
  用雪花算法的id%16，似乎只能命中0和1，不知道是电脑的原因还是？

2. （必做）基于 hmily TCC 或 ShardingSphere 的 Atomikos XA 实现一个简单的分布式事务应用 demo（二选一），提交到 Github。 
   - 作业路径：https://github.com/gugongerguo/JAVA-01/tree/main/Week_08/multdatabasestables

