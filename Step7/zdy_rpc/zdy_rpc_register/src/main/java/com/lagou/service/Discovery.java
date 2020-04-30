package com.lagou.service;

import com.lagou.service.com.lagou.strategy.LeastResponseTimeStrategy;
import com.lagou.service.com.lagou.strategy.LoadBalanceStrategy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lagou.constants.Consts.*;

/**
 * @ClassName Discovery
 * @Description TODO
 * @Author decamincow
 * @Date 2020/4/30 6:21 AM
 * @Version 1.0
 **/
public class Discovery {

    List<String> serviceUrls = new ArrayList();
    Map<String, Long> serviceDataMap = new HashMap<String, Long>();

    private CuratorFramework curatorFramework;

    private LoadBalanceStrategy loadBalanceStrategy;

    {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(ZK_CONNECT_STRING)
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 10))
                .build();
        curatorFramework.start();
        loadBalanceStrategy = new LeastResponseTimeStrategy();

    }

    public void init(String serviceName) throws Exception {
        String servicePath = REGISTER_PATH + "/" + serviceName;
        serviceUrls = curatorFramework.getChildren().forPath(servicePath);
        initAddressAndDataCache(servicePath);
        registerWatcher(servicePath);
    }

    private void registerWatcher(final String servicePath) throws Exception {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, servicePath, true);
        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                serviceUrls = curatorFramework.getChildren().forPath(servicePath);
                initAddressAndDataCache(servicePath);
                System.out.println(serviceDataMap);
//                for(String address : serviceUrls){
//                    System.out.println(address);
//                    System.out.println(new String(curatorFramework.getData().forPath(servicePath + "/" +address)));
//                }

            }
        };
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        pathChildrenCache.start();

    }

    public String getEndPoint(){
        return loadBalanceStrategy.selectHost(serviceDataMap);
    }

    private void initAddressAndDataCache(String servicePath) throws Exception {
        serviceUrls = curatorFramework.getChildren().forPath(servicePath);
        System.out.println(serviceUrls);
        for(String address : serviceUrls){
            serviceDataMap.put(address, Long.parseLong(new String(curatorFramework.getData().forPath(servicePath + "/" +address))));
        }
    }

    public static void main(String[] args) throws Exception {

        Discovery discovery = new Discovery();
        discovery.init(SERVICE_NAME);
        for (int i=0; i<30; i++){
            String endPoint = discovery.getEndPoint();
            System.out.println(endPoint);
            Thread.sleep(2000);
        }

    }

}
