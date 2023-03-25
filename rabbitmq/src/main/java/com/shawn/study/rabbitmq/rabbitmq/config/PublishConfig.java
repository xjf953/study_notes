package com.shawn.study.rabbitmq.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 发布订阅模式配置文件
 * @author Shawn
 * @create 2023/3/18 18:11
 */
@Configuration
public class PublishConfig {

    //定义 扇形交换器后  再定义三个队列，实现生产者发布消息  多个队列订阅消费
    //将队列跟交换器进行绑定
    //类似广播

    /**
     *  定义扇形交换器交换器(fanout)
     * @author Shawn
     * @date 2023-03-25 14:13
     * @param
     * @return * @return FanoutExchange
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("PUBLISH_SUBSCRIBE_EXCHANGE");
    }

    @Bean
    public Queue psFirstQueue() {
        return new Queue("psFirstQueue");
    }

    @Bean
    public Queue psSecondQueue() {
        return new Queue("psSecondQueue");
    }

    @Bean
    public Queue psThirdQueue() {
        return new Queue("psThirdQueue");
    }

    @Bean
    public Binding routingFirstBinding() {
        return BindingBuilder.bind(psFirstQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding routingSecondBinding() {
        return BindingBuilder.bind(psSecondQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding routingThirdBinding() {
        return BindingBuilder.bind(psThirdQueue()).to(fanoutExchange());
    }

}
