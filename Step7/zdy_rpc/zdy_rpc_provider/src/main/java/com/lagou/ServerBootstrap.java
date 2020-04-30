package com.lagou;

import com.lagou.service.RegistryImpl;
import com.lagou.service.UserServiceImpl;
import com.lagou.service.impl.Registry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.lagou.constants.Consts.SERVICE_NAME;

@SpringBootApplication
public class ServerBootstrap {

    static String host = "127.0.0.1";
    static int port = 8992;

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ServerBootstrap.class, args);
        UserServiceImpl.startServer(host,port);

        // 注册服务器信息到 zk
        Registry registry = new RegistryImpl();
        registry.register(SERVICE_NAME, host + ":" + port);
    }
}