package com.decamincow.learning.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @ClassName AutodeliverController
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/25 3:10 AM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/autodeliver")
public class AutodeliverController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    // /autodeliver/checkState/1545132
    @GetMapping("/checkState/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId) {

        // 使用负载均衡的实现方式
        String url = "http://resume/resume/openstate/" + userId;
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        System.out.println("======>>从 Eureka 获取的 url:" + url);
        return forObject;

//        // 不适用负载均衡的实现方式
//        // 1, 获取 Eureka 实例列表
//        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("resume");
//        // 2, 选择一个实例，这是负载均衡的过程
//        ServiceInstance serviceInstance = serviceInstances.get(0);
//        // 3, 从元数据取 host port
//        String host = serviceInstance.getHost();
//        int port = serviceInstance.getPort();
//
//        String url = "http://"+ host +":"+ port +"/resume/openstate/" + userId;
//        Integer forObject = restTemplate.getForObject(url, Integer.class);
//        System.out.println("======>>从 Eureka 获取的 url:" + url);
//        return forObject;
    }

    /**
     * 提供者模拟处理超时，调用方法添加Hystrix控制 * @param userId
     * @return
     */
    // 使用@HystrixCommand注解进行熔断控制
    @HystrixCommand(
            // 线程池标识，要保持唯一，不唯一的话就共用了
            threadPoolKey = "findResumeOpenStateTimeout",
            // 线程池细节属性配置
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize",value = "1"), // 线程 @HystrixProperty(name="maxQueueSize",value="20") // 等
            },
            // commandProperties熔断的一些细节属性配置
            commandProperties = {
                    // 每一个属性都是一个HystrixProperty
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",
                            value="2000")
            } )
    @GetMapping("/checkStateTimeout/{userId}")
    public Integer findResumeOpenStateTimeout(@PathVariable Long userId) {
// 使用ribbon不需要我们自己获取服务实例然后选择一个那么去访问了(自己的负载均衡)
        String url = "http://lagou-service-resume/resume/openstate/" + userId; // 指定服务名
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }

    @GetMapping("/checkStateTimeoutFallback/{userId}")
    @HystrixCommand(
            // 线程池标识，要保持唯一，不唯一的话就共用了
            threadPoolKey = "findResumeOpenStateTimeoutFallback",
            // 线程池细节属性配置
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize",value = "2"), // 线程数
                    @HystrixProperty(name="maxQueueSize",value="20") // 等待队列长度
            },
            // commandProperties熔断的一些细节属性配置
            commandProperties = {
                    // 每一个属性都是一个HystrixProperty
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="2000"),
                    // hystrix高级配置，定制工作过程细节
                    // 统计时间窗口定义
                    @HystrixProperty(name =
                            "metrics.rollingStats.timeInMilliseconds",value = "8000"), // 统计时间窗口内的最小请求数
                    @HystrixProperty(name =
                            "circuitBreaker.requestVolumeThreshold",value = "2"),
                    // 统计时间窗口内的错误数量百分比阈值
                    @HystrixProperty(name =
                            "circuitBreaker.errorThresholdPercentage",value = "50"),
                    // 自我修复时的活动窗口⻓度
                    @HystrixProperty(name =
                            "circuitBreaker.sleepWindowInMilliseconds",value = "3000")
            },
            fallbackMethod = "myFallBack" // 回退方法数待队列⻓度
    )
    public Integer findResumeOpenStateTimeoutFallback(@PathVariable Long
                                                              userId) {
        // 使用ribbon不需要我们自己获取服务实例然后选择一个那么去访问了(自己的负载均衡)
        String url = "http://lagou-service-resume/resume/openstate/" + userId; // 指定服务名
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }

    /*
    定义回退方法，返回预设默认值
    注意:该方法形参和返回值与原始方法保持一致
    */
    public Integer myFallBack(Long userId) {
        return -123333; // 兜底数据
    }

    @GetMapping("/instances")
    public List<ServiceInstance> showInfo() {
        return this.discoveryClient.getInstances("resume");
    }
}