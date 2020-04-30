package com.decamincow.registry;

import com.decamincow.registry.client.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class RegistryApplicationTests {

    @Autowired
    Client client;

    @Test
    void contextLoads() throws Exception {
        client.register();
        client.registerWatcher();
        System.in.read();
    }

}
