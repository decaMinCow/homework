package com.lagou.client;

import com.lagou.service.Discovery;
import com.lagou.service.UserService;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import static com.lagou.constants.Consts.*;

public class ClientBootStrap {

    private static UserService proxy;
    private static RpcConsumer rpcConsumer;
    private static Discovery discovery;

    private static ClientBootStrap singleton = null;

    private CuratorFramework curatorFramework;

    {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(ZK_CONNECT_STRING)
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 10))
                .build();
        curatorFramework.start();
    }

    private ClientBootStrap() throws Exception {
        discovery = new Discovery();
        discovery.init(SERVICE_NAME);
    }
    public static ClientBootStrap getInstance() throws Exception {
        if (singleton == null) {
            singleton = new ClientBootStrap();
        }
        return singleton;
    }

    public static void main(String[] args) throws Exception {

        ClientBootStrap.getInstance().consumerInit();

    }

    public void consumerInit() throws Exception {
        // 获取服务端地址,封装负载策略
        String endPoint = discovery.getEndPoint();
        System.out.println(endPoint);
        String host = endPoint.split(":")[0];
        String port = endPoint.split(":")[1];

        Long requestTimeStamp = System.currentTimeMillis();
        rpcConsumer = new RpcConsumer(host, Integer.parseInt(port), null);
        proxy = (UserService) rpcConsumer.createProxy(UserService.class);

        // 更新响应时间到 zk 相应节点
        Long responseTime = System.currentTimeMillis() - requestTimeStamp;
        updateResponseTime(SERVICE_NAME, endPoint, responseTime.toString().getBytes());

        System.out.println("响应时间:" + responseTime);
        System.out.println(proxy.sayHello("are you ok?"));
    }

    public void updateResponseTime(String serviceName, String serviceAddress, byte[] responseTime) throws Exception {
        String servicePath = REGISTER_PATH + "/" + serviceName;
        curatorFramework.setData().forPath(servicePath+"/"+serviceAddress, responseTime);
    }

}