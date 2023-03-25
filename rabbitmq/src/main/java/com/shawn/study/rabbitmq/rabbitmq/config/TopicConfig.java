package com.shawn.study.rabbitmq.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 主题模式配置
 * @author Shawn
 * @create 2023/3/25 15:00
 */
@Configuration
public class TopicConfig {

    //主题模式，整体与路由模式非常相似
    //首先仍然创建三个队列与主题交换机(Topic)
    //将队列与交换机进行绑定，同时将会指定通配符，通配符有两种： * 表示可以匹配一个  # 表示可以匹配多个


    //#.com表示接收多个以.com结尾的字段。
    //例如：taobao.com、www.taobao.com、www.jd.com。
    //        *.com表示接收一个以.com结尾的字段。
    //例如：taobao.com、jd.com。
    //多个字段是无法匹配的，比如www.taobao.com、cn.taobao.com。

    //www.#可以匹配多个以www开头的字段。
    //例如www.taobao.com、www.jd。
    //www.*可以匹配一个以www开头的字段。
    //例如：www.taobao、www.jd。
    //多个字段是无法匹配的,比如www.taobao.com、www.jd.com。

    @Bean
    public Queue topicFirstQueue() {
        return new Queue("topicFirstQueue");
    }

    @Bean
    public Queue topicSecondQueue() {
        return new Queue("topicSecondQueue");
    }

    @Bean
    public Queue topicThirdQueue() {
        return new Queue("topicThirdQueue");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Binding topicFirstBind() {
        // .com 为结尾
        return BindingBuilder.bind(topicFirstQueue()).to(topicExchange()).with("*.com");
    }

    @Bean
    public Binding topicSecondBind() {
        // www.为开头
        return BindingBuilder.bind(topicSecondQueue()).to(topicExchange()).with("www.*");
    }
//
//    @Bean
//    public Binding topicThirdBind() {
//        // www.为开头
//        return BindingBuilder.bind(topicThirdQueue()).to(topicExchange()).with("www.*");
//    }
}
