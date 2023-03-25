package com.shawn.study.rabbitmq.rabbitmq.service;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class Consumer {


    /**
     * 消费队列
     * @author Shawn
     * @date 2023-03-18 17:23
     * @param message 消息
     * @return
     */
    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue("notice_queue"))
    public void process(String message) {
        //@RabbitListener 注解创建监听器，绑定 @Queue所指定队列
        //RabbitListener 注解提供了@QueueBinding、@Queue、@Exchange 等对象，通过这个组合注解配置交换机、绑定路由并且配置监听功能等
        //@RabbitHandler 注解为具体接收的方法
        System.out.println("Consumer收到通知：" + message);
    }

    /**
     *  简单模式  消费
     * @author Shawn
     * @date 2023-03-25 13:11
     * @param
     * @return
     */
    @RabbitListener(queuesToDeclare = @Queue("simple"))
    public void simpleConsumer(String message){
        System.out.println("简单模式Consumer收到通知：" + message);
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<工作模式start>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//


    /**
     * 工作模式 消费1
     * @author Shawn
     * @date 2023-03-25 13:19
     * @param message
     * @return
     */
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void workConsume(String message) {
        System.out.println("工作模式first:" + message);
    }

    /**
     * 工作模式 消费2
     *  两个监听器监听同一个队列名称，一条消息只会由一个消费，类似负载均衡
     * @author Shawn
     * @date 2023-03-25 13:20
     * @param message
     * @return
     */
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void workConsumeSecond(String message) {
        System.out.println("工作模式second:" + message);
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<工作模式end>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//


    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<发布订阅模式start>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//


    /**
     * 监听绑定到交换机上的队列进行消费
     * @author Shawn
     * @date 2023-03-25 14:30
     * @param message
     * @return
     */
    @RabbitListener(queues = "psFirstQueue")
    public void pubsubQueueFirst(String message) {
        System.out.println("【first】:" + message);
    }

    @RabbitListener(queues = "psSecondQueue")
    public void pubsubQueueSecond(String message) {
        System.out.println("【second】:" + message);
    }

    @RabbitListener(queues = "psThirdQueue")
    public void pubsubQueueThird(String message) {
        System.out.println("【third】:" + message);
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<发布订阅模式end>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//


    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<路由模式end>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//

    @RabbitListener(queues = "routingFirstQueue")
    public void routingFirstListener(String message) {
        System.out.println("【routing first】" + message);
    }

    @RabbitListener(queues = "routingSecondQueue")
    public void routingSecondListener(String message) {
        System.out.println("【routing second】" + message);
    }

    @RabbitListener(queues = "routingThirdQueue")
    public void routingThirdListener(String message) {
        System.out.println("【routing third】" + message);
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<路由模式end>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<主题模式start>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//

    @RabbitListener(queues = "topicFirstQueue")
    public void topicFirstListener(String message) {
        System.out.println("【topic first】" + message);
    }

    @RabbitListener(queues = "topicSecondQueue")
    public void topicSecondListener(String message) {
        System.out.println("【topic second】" + message);
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<主题模式end>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//

    /**
     * RPC模式 可返回消息
     * @author Shawn
     * @date 2023-03-25 15:35
     * @param message
     * @return * @return String
     */
    @RabbitListener(queuesToDeclare =@Queue("rpcQueue"))
    public String rpcListener(String message) {
        System.out.println("【rpc接收消息】" + message);
        return "rpc 返回： 消费成功";
    }
}
