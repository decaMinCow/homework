## 一、编程题

1. 基于SpringBoot整合SSS框架（即整合第一阶段模块三作业第二题内容，含有登录拦截验证）
2. 在 1 的基础上开发SpringSession进行Session一致性控制
3. 将工程打成war包
4. 将war包部署到分布式集群架构中，要求一个Nginx节点，两个Tomcat节点
      —> Nginx（轮询策略） —> Tomcat1—> Tomcat2
5. 完成测试

[工程地址](https://github.com/decaMinCow/homework/tree/master/Step3/sss) 
> 因为用的 springboot 起的项目所以没有打 war 包部署，本人是 docker 起的 nginx可能配置文件需要做相应更改 （ip 没有写成 127.0.0.1 因为这样发现不了宿主机），项目已测试没有问题

## 二、简答题

### 1、请描述你对分布式调度的理解（结合Elastic-Job-lite图文并茂描述）

#### 概述

Elastic-Job是一个分布式调度解决方案，由两个相互独立的子项目Elastic-Job-Lite和Elastic-Job-Cloud组成。

Elastic-Job-Lite定位为轻量级无中心化解决方案，使用jar包的形式提供分布式任务的协调服务。

Elastic-Job-Cloud使用Mesos + Docker的解决方案，额外提供资源治理、应用分发以及进程隔离等服务。

#### 功能列表

##### 1. Elastic-Job-Lite

* 分布式调度协调
* 弹性扩容缩容
* 失效转移
* 错过执行作业重触发
* 作业分片一致性，保证同一分片在分布式环境中仅一个执行实例
* 自诊断并修复分布式不稳定造成的问题
* 支持并行调度
* 支持作业生命周期操作
* 丰富的作业类型
* Spring整合以及命名空间提供
* 运维平台

##### 2. Elastic-Job-Cloud
* 应用自动分发
* 基于Fenzo的弹性资源分配
* 分布式调度协调
* 弹性扩容缩容
* 失效转移
* 错过执行作业重触发
* 作业分片一致性，保证同一分片在分布式环境中仅一个执行实例
* 支持并行调度
* 支持作业生命周期操作
* 丰富的作业类型
* Spring整合
* 运维平台
* 基于Docker的进程隔离(TBD)

#### 架构图

## Elastic-Job-Lite

![Elastic-Job-Lite Architecture](http://elasticjob.io/docs/elastic-job-lite/img/architecture/elastic_job_lite.png)


#### [Release Notes](https://github.com/elasticjob/elastic-job/releases)

#### [Roadmap](ROADMAP.md)

#### 快速入门

##### 引入maven依赖

```xml
<!-- 引入elastic-job-lite核心模块 -->
<dependency>
    <groupId>io.elasticjob</groupId>
    <artifactId>elastic-job-lite-core</artifactId>
    <version>${latest.release.version}</version>
</dependency>

<!-- 使用springframework自定义命名空间时引入 -->
<dependency>
    <groupId>io.elasticjob</groupId>
    <artifactId>elastic-job-lite-spring</artifactId>
    <version>${latest.release.version}</version>
</dependency>
```

##### 作业开发

```java
public class MyElasticJob implements SimpleJob {
    
    @Override
    public void execute(ShardingContext context) {
        switch (context.getShardingItem()) {
            case 0: 
                // do something by sharding item 0
                break;
            case 1: 
                // do something by sharding item 1
                break;
            case 2: 
                // do something by sharding item 2
                break;
            // case n: ...
        }
    }
}
```

##### 作业配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:reg="http://www.dangdang.com/schema/ddframe/reg"
    xmlns:job="http://www.dangdang.com/schema/ddframe/job"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
                        http://www.springframework.org/schema/beans/spring-beans.xsd
                        http://www.dangdang.com/schema/ddframe/reg
                        http://www.dangdang.com/schema/ddframe/reg/reg.xsd
                        http://www.dangdang.com/schema/ddframe/job
                        http://www.dangdang.com/schema/ddframe/job/job.xsd
                        ">
    <!--配置作业注册中心 -->
    <reg:zookeeper id="regCenter" server-lists="yourhost:2181" namespace="dd-job" base-sleep-time-milliseconds="1000" max-sleep-time-milliseconds="3000" max-retries="3" />
    
    <!-- 配置作业-->
    <job:simple id="oneOffElasticJob" class="xxx.MyElasticJob" registry-center-ref="regCenter" cron="0/10 * * * * ?" sharding-total-count="3" sharding-item-parameters="0=A,1=B,2=C" />
</beans>
```