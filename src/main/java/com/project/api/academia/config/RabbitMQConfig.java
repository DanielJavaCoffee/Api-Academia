package com.project.api.academia.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCG_NAME = "EXCG.direct";
    public static final String QUEU_NAME_EMAIL_LOG = "email.log";
    public static final String RK_EMAIL_LOG = "email.log";


    @Bean
    public Queue queue(){
        return new Queue(QUEU_NAME_EMAIL_LOG, true);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(EXCG_NAME);
    }

    @Bean
    public Binding binding(){
        return  BindingBuilder
                .bind(queue())
                .to(directExchange())
                .with(RK_EMAIL_LOG);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
