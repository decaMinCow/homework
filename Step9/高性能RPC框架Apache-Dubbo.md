## 作业说明
0. 作业我是按照 dubbo 的注解方式实现的
1. 两个作业都放到一个工程来实现的，没有严格按照老师要求来做，把导师要考察的核心内容都实现了
2. 因为输出的东西太多了，我把log日志都屏蔽掉了，所以启动项目控制台没有显示成功其实可能已经启动了

## 一、编程题

   通过扩展Dubbo的Filter（TransportIPFilter），完成Web请求的真实IP透传到Dubbo服务当中，并在Dubbo服务中打印请求的IP

题目要求：

1. 构建一个Web项目（A），提供一个HTTP接口；构建2个Dubbo服务（B和C），各提供一个Dubbo接口，被Web项目调用（如下图所示）

2. 从Web项目获取请求的IP，通过TransportIPFilter完成把IP设置到RpcContext中

3. 在两个Dubbo项目（B和C）中，从RpcContext获取IP并打印到控制台，B和C都应该正确打印IP

4. 注意：不可在业务方法调用Dubbo接口前采用硬编码的方式设置IP

[工程地址](https://github.com/decaMinCow/homework/tree/master/Step9/transport_ip_filter_demo) 

> 启动 AnnotationConsumerBootstrap 类
> 
> 访问 http://localhost:8080/api/hello 测试便可


## 二、编程题

   在真实业务场景中，经常需要对各个业务接口的响应性能进行监控（常用指标为：TP90、TP99）
下面通过扩展Dubbo的Filter（TPMonitorFilter），完成简易版本 Dubbo 接口方法级性能监控，记录下TP90、TP99请求的耗时情况

题目要求：

1. 编写一个Dubbo服务，提供3个方法（methodA、methodB、methodC），每方法都实现了随机休眠0-100ms

2. 编写一个消费端程序，不断调用Dubbo服务的3个方法（建议利用线程池进行并行调用，确保在1分钟内可以被调用2000次以上）

3. 利用TPMonitorFilter在消费端记录每个方法的请求耗时时间（异步调用不进行记录）

4. 每隔5s打印一次最近1分钟内每个方法的TP90、TP99的耗时情况

[工程地址](https://github.com/decaMinCow/homework/tree/master/Step9/transport_ip_filter_demo) 

> 启动 AnnotationActionTests 测试类便可