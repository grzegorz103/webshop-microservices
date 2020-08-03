package service.event.listeners;

import microservices.common.config.ExchangeNames;
import microservices.common.config.QueueNames;
import microservices.common.config.RoutingKeyNames;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConfig {

    @Bean
    Queue createQueue() {
        return new Queue(QueueNames.EVENT_CREATE_QUEUE, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(ExchangeNames.EVENT_EXCHANGE);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(RoutingKeyNames.EVENT_CREATE_KEY);
    }

}
