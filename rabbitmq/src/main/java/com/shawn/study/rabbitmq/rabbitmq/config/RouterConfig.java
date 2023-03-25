package com.shawn.study.rabbitmq.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 路由模式
 * @author Shawn
 * @create 2023/3/25 14:39
 */
@Configuration
public class RouterConfig {
    //路由模式  创建多个队列
    //创建路由交换器(Direct)
    //将队列绑定到交换器，同时指定路由key
    //消息生产时，会指定路由，交换器通过路由进行匹配 发送到指定队列
    //不具备模糊匹配的能力，routingKey不匹配  无法到队列

    @Bean
    public Queue routingFirstQueue() {
        return new Queue("routingFirstQueue");
    }

    @Bean
    public Queue routingSecondQueue() {
        return new Queue("routingSecondQueue");
    }

    @Bean
    public Queue routingThirdQueue() {
        return new Queue("routingThirdQueue");
    }

    @Bean
    public DirectExchange routingExchange() {
        return new DirectExchange("routingExchange");
    }

    @Bean
    public Binding routingFirstBind() {
        return BindingBuilder.bind(routingFirstQueue()).to(routingExchange()).with("firstRouting");
    }

    @Bean
    public Binding routingSecondBind() {
        return BindingBuilder.bind(routingSecondQueue()).to(routingExchange()).with("secondRouting");
    }

    @Bean
    public Binding routingThirdBind() {
        return BindingBuilder.bind(routingThirdQueue()).to(routingExchange()).with("thirdRouting");
    }
}
