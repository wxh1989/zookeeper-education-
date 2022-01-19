package com.zookeeper.controller;

import com.zookeeper.config.WrapperZK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class client {

    @Autowired
    private WrapperZK wrapperZK;

    @GetMapping(value = "/zookeeper/test")
    public String test(){
        return "install zookeeper"+wrapperZK.getConnectString();
    }

}
