package com.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.prefs.NodeChangeListener;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ZookeeperLock {
    @Autowired
    CuratorFramework curatorFramework;
    @Test
    public void addNodeListener()throws Exception{

        TreeCache cache = TreeCache.newBuilder(curatorFramework, "/node-lock").setCacheData(false).build();
        cache.getListenable().addListener((c, event) -> {
            if ( event.getData() != null )
            {
                System.out.println("type=" + event.getType() + " path=" + event.getData().getPath());
            }
            else
            {
                System.out.println("type=" + event.getType());
            }
        });
        cache.start();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        in.readLine();

    }
    @Test
    public void getReadLock() throws  Exception{
        InterProcessReadWriteLock readWriteLock = new InterProcessReadWriteLock(curatorFramework,"/lock-node");
        InterProcessLock lock = readWriteLock.readLock();
        System.out.println("等待获取读锁");
        //获取锁
        lock.acquire();
        for(int i = 0 ; i < 100; i++){
            Thread.sleep(3000);
            System.out.println("处理业务逻辑中"+i);
        }
        //释放锁
        lock.release();
        System.out.println("等待释放锁!");
    }


    @Test
    public void getWriteLock() throws  Exception{
        InterProcessReadWriteLock writeLock = new InterProcessReadWriteLock(curatorFramework,"/lock-node");
        InterProcessLock lock = writeLock.writeLock();
        System.out.println("等待获取写锁");
        //获取锁
        lock.acquire();
        for(int i = 0 ; i < 100; i++){
            Thread.sleep(3000);
            System.out.println("处理业务逻辑中"+i);
        }
        //释放锁
        lock.release();
        System.out.println("等待释放锁!");
    }




}
