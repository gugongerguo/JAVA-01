学习笔记

Week03 作业题目（周三）：

1.（必做）整合你上次作业的 httpclient/okhttp；
https://github.com/gugongerguo/JAVA-01/blob/main/Week_03/java/src/main/java/geek/week3/gateway1/HttpClientHelper.java

2.（选做）使用 netty 实现后端 http 访问（代替上一步骤）
https://github.com/gugongerguo/JAVA-01/blob/main/Week_03/java/src/main/java/geek/week3/gateway1/netty/NettyHttpServer.java

Week03 作业题目（周日）：

1.（必做）实现过滤器。

https://github.com/gugongerguo/JAVA-01/tree/main/Week_03/java/src/main/java/geek/week3/gateway2

2.（选做）实现路由。

3.（选做）跑一跑课上的各个例子，加深对多线程的理解

4.（选做）完善网关的例子，试着调整其中的线程池参数



***1.对高性能的理解：***

    1.1高并发用户 系统表现出来的支持更多用户同时使用
    1.2高吞吐量 QPS每秒请求量
    1.3低延迟  高频交易系统 
    1.4以上整体称作高并发

***2.压测 wrk -c 40 -d 30s --latency http://localhost:3000***

    2.1 -c 并发量
    2.2 通过分析延迟p50 p90 p99，来查看延迟的分布，单单只看平均延迟不太准确
    2.3 一般延迟越低吞吐越高，对于多个系统来回调用就不能只根据延迟来判断吞吐量
    注意：延迟和响应时间的差别，延迟是系统内部，响应时间还要加上网络通信时间

***3.高性能副作用***

    3.1 复杂度高
    3.2 建设维护成本高
    3.3 一旦出现bug后果很严重
    如何应对，混沌工程～疫苗
    https://zhuanlan.zhihu.com/p/55612414
    http://www.uml.org.cn/zjjs/202004141.asp?artid=23163

***4.netty***

**支持模式**

```
Reator单线程模式
Reactor多线程模式
Reactor多线程主从模式
```

未完待续。。。。。
