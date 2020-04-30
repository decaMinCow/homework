package com.decamincow.registry.client;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONObject;
import com.decamincow.registry.conf.MysqlConfig;
import com.decamincow.registry.util.SpringContextUtil;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.decamincow.registry.Consts.*;

/**
 * @ClassName Client
 * @Description TODO
 * @Author decamincow
 * @Date 2020/4/30 3:49 PM
 * @Version 1.0
 **/
@Component
public class Client {

    @Autowired
    MysqlConfig mysqlConfig;

    private CuratorFramework curatorFramework;

    {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(ZK_CONNECT_STRING)
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 10))
                .build();
        curatorFramework.start();
    }

    public void register() {
        try {
            if(curatorFramework.checkExists().forPath(SERVICE_PATH) == null){
                curatorFramework.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.EPHEMERAL)
                        .forPath(SERVICE_PATH, JSONObject.toJSONString(mysqlConfig).getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerWatcher() throws Exception {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, REGISTER_PATH, true);
        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
            // TODO 时间比较赶，先粗鲁的实现了
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                String jsonString = new String(pathChildrenCacheEvent.getData().getData());
                System.out.println(jsonString);
                MysqlConfig mysqlConfig = JSONObject.parseObject(jsonString, MysqlConfig.class);
                System.out.println(mysqlConfig);
                DruidDataSource datasource = (DruidDataSource) SpringContextUtil.getBean("getDataSource");
                datasource.setUrl(mysqlConfig.getUrl());
                datasource.setDriverClassName(mysqlConfig.getDriverClassName());
                datasource.setUsername(mysqlConfig.getUsername());
                datasource.setPassword(mysqlConfig.getPassword());
                datasource.restart();

                
            }
        };
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        pathChildrenCache.start();

    }

    public static void main(String[] args) throws Exception {
        Client registry = new Client();
        registry.register();
        registry.registerWatcher();
        System.in.read();

    }
}
