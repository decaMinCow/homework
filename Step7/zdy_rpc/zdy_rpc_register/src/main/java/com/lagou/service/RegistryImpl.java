package com.lagou.service;

import com.lagou.service.impl.Registry;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.io.IOException;

import static com.lagou.constants.Consts.REGISTER_PATH;
import static com.lagou.constants.Consts.SERVICE_NAME;
import static com.lagou.constants.Consts.ZK_CONNECT_STRING;

/**
 * @ClassName RegistryImpl
 * @Description TODO
 * @Author decamincow
 * @Date 2020/4/30 5:30 AM
 * @Version 1.0
 **/
public class RegistryImpl implements Registry {

    private CuratorFramework curatorFramework;

    {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(ZK_CONNECT_STRING)
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 10))
                .build();
        curatorFramework.start();
    }

    public void register(String serviceName, String serviceAddress) {
        String servicePath = REGISTER_PATH + "/" + serviceName;
        try {
            if(curatorFramework.checkExists().forPath(servicePath) == null){
                curatorFramework.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(servicePath, "0".getBytes());
            }
            String addressPath = servicePath + "/" + serviceAddress;
            String rs = curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(addressPath, "0".getBytes());
            System.out.println(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws IOException {
        
        Registry registry = new RegistryImpl();
        registry.register(SERVICE_NAME, "192.168.88.4");
        System.in.read();

    }
}
