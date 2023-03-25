package com.shawn.study.rabbitmq.rabbitmq.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Shawn
 * @create 2023/3/18 17:28
 */
@SpringBootTest
public class RabbitMqTest {

    @Autowired
    Producer producer;

    /**
     *  简单模式测试
     * @author Shawn
     * @date 2023-03-25 14:32
     * @param
     * @return
     */
    @Test
    void simpleTest(){
        producer.simple();
    }

    /**
     * 工作模式测试
     * @author Shawn
     * @date 2023-03-25 14:32
     * @param
     * @return
     */
    @Test
    void workTest(){
        producer.work();
    }

    /**
     * 发布订阅模式测试
     * @author Shawn
     * @date 2023-03-25 14:32
     * @param
     * @return
     */
    @Test
    void publishTest(){
        producer.publish();
    }

    /**
     * 路由模式测试
     * @author Shawn
     * @date 2023-03-25 14:55
     * @param
     * @return
     */
    @Test
    void routerTest(){
        producer.router();
    }

    /**
     * 主题模式测试
     * @author Shawn
     * @date 2023-03-25 15:27
     * @param
     * @return
     */
    @Test
    void topicTest() {
        producer.topic();
    }

    /**
     * RPC模式测试
     * @author Shawn
     * @date 2023-03-25 15:36
     * @param
     * @return
     */
    @Test
    void rpcTest() {
        producer.rpc();
    }
}
