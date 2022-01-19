package com.zookeeper;

import org.apache.zookeeper.client.ZKClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZookeeperMain {

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperMain.class, args);
    }
}
