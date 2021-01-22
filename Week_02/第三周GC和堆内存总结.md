***SerialGC 串行GC***

java -XX:+UseSerialGC -Xms256m -Xmx256m -Xloggc:gc.serial.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

正在执行...
执行结束!共生成对象次数:4475

GC：表明进行了一次垃圾回收，前面没有Full修饰，表明这是一次Minor GC ,注意它不表示只GC新生代，并且现有的不管是新生代还是老年代都会STW。

Allocation Failure：表明本次引起GC的原因是因为在年轻代中没有足够的空间能够存储新的数据了。

DefNew：新生代回收，回收前->回收后（Xmn即新生代最大容量）

Tenured：永久带回收

Metaspace：元空间回收

总共GC 25次
第一阶段：youngGC，每次大概有几十M，没有回收直接进入老年代

第二阶段：回收不掉直接干到老年代

第三阶段：老年代回收又被填满，最终整个heap占满，OOM

**调大heap**

java -XX:+UseSerialGC -Xms350m -Xmx350m -Xloggc:gc.serial.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

正在执行...
执行结束!共生成对象次数:6017

***ParallelGC 并行GC***

java -XX:+UseParallelGC -Xms350m -Xmx350m -Xloggc:gc.parallel.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

正在执行...
执行结束!共生成对象次数:4776

***ConcMarkSweepGC CMS GC***

java -XX:+UseConcMarkSweepGC -Xms350m -Xmx350m -Xloggc:gc.CMS.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

正在执行...
执行结束!共生成对象次数:5845

***G1 GC***

java -XX:+UseG1GC -Xms350m -Xmx350m -Xloggc:gc.G1.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

正在执行...
执行结束!共生成对象次数:5158

***总结***

SerialGC 串行GC 相对来说吞吐量最优

其他GC为了优化响应时间，妥协了吞吐量

需要结合实际场景选择**响应优先**还是**吞吐量优先**
