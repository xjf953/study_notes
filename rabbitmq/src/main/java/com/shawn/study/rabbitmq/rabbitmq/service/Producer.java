package com.shawn.study.rabbitmq.rabbitmq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;



    /**
     * 1.简单模式生产消息
     * @author Shawn
     * @date 2023-03-25 13:15
     * @param
     * @return
     */
    public void simple(){
        String message = "(simple) 一条消息";
        System.out.println("shawn：" + message);
        //简单模式点对点传输，不需要指定交换机
        //队列名称  消息
        rabbitTemplate.convertAndSend("simple", message);
    }

    /**
     * 2.工作模式生产消息
     * @author Shawn
     * @date 2023-03-25 13:35
     * @param
     * @return
     */
    public void work() {
        String message = "(work) 一条消息";
        System.out.println("shawn：" + message);
        rabbitTemplate.convertAndSend("work",message);
        rabbitTemplate.convertAndSend("work", "(work) 第二条消息");
    }

    /**
     *
     * 发布订阅模式生产消息
     * @author Shawn
     * @date 2023-03-25 14:26
     * @param
     * @return
     */
    public void publish(){
        String message = "(publish) 发布一条消息";
        System.out.println("shawn：" + message);
        // 参数分别指定 交换器名称  路由名称无需指定(null)  消息
        rabbitTemplate.convertAndSend("PUBLISH_SUBSCRIBE_EXCHANGE", null, message);
    }

    /**
     * 路由模式生产者
     * @author Shawn
     * @date 2023-03-25 14:44
     * @param
     * @return
     */
    public void router(){
        String message = "(routing) 一条消息";
        // 参数分别指定 交换器名称 路由键  消息
        // 交换器会根据消息指定的routingKey找到对应绑定的队列进行消费
        rabbitTemplate.convertAndSend("routingExchange","firstRouting",message.concat("first routing"));
        rabbitTemplate.convertAndSend("routingExchange","secondRouting",message.concat("second routing"));
        rabbitTemplate.convertAndSend("routingExchange","thirdRouting",message.concat("third routing"));
    }

    /**
     * 主题模式
     * @author Shawn
     * @date 2023-03-25 15:19
     * @param
     * @return
     */
    public void topic(){
        // 参数分别为 交换器名称 路由键 消息
        rabbitTemplate.convertAndSend("topicExchange","www.taobao.com","www.taobao.com");
        rabbitTemplate.convertAndSend("topicExchange","taobao.com","taobao.com");
        rabbitTemplate.convertAndSend("topicExchange","www.jd","www.jd");
    }

    /** 
     * RPC模式
     * @author Shawn
     * @date 2023-03-25 15:32
     * @param 
     * @return 
     */
    public void rpc(){
        // 参数分别为 路由键 消息
        // RPC模式可接收消费者返回值
        Object receive = rabbitTemplate.convertSendAndReceive("rpcQueue","rpc send message");
        System.out.println("【收到消费者消息】" + receive);
    }
}
