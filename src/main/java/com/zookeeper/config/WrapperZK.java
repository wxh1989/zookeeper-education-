package com.zookeeper.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//此注解 节省 实体类中的get set 方法
@Data
//相当于 spring 配置文件中的 bean 让spring 管理
@Component
@ConfigurationProperties(prefix = "curator")
public class WrapperZK {

    private int retryCount;

    private int elapsedTimeMs;

    private String connectString;

    private int sessionTimeoutMs;

    private int connectionTimeoutMs;

}
