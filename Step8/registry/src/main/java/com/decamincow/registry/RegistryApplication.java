package com.decamincow.registry;

import com.decamincow.registry.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.StandardEnvironment;

import static com.decamincow.registry.Consts.SERVICE_NAME;

@SpringBootApplication
public class RegistryApplication implements CommandLineRunner {

    @Autowired
    private Client client;

    public static void main(String[] args) {
        SpringApplication.run(RegistryApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        client.register();
        client.registerWatcher();
        System.in.read();
    }

}
