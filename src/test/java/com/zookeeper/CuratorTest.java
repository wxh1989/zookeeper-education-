package com.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.nio.charset.StandardCharsets;

//单元测试包名 必须 和 启动类的包名一直
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CuratorTest {



    @Autowired
    CuratorFramework curatorFramework;

    @Test
    public void createZKNode() throws Exception {
        curatorFramework.delete().forPath("/curator-node");
        //添加持久化节点
        String path = curatorFramework.create().forPath("/curator-node");
        //添加序号临时节点 withMode 创建节点的模式 CreateMode.EPHEMERAL_SEQUENTIAL 临时序号节点
        String path1 = curatorFramework.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/curator-node-temp");
        System.out.println(String.format("###############"+"create node :%s successfully",path));
    }

    @Test
    public void setZKNodeValue()throws Exception{
        curatorFramework.setData().forPath("/curator-node","curator客户端".getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void getZKNode() throws Exception{
        byte[] bytes = curatorFramework.getData().forPath("/curator-node");
        System.out.println("###############"+new String(bytes));
    }

}
