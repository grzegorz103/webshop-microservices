package price.service.events;

import microservices.common.config.ExchangeNames;
import microservices.common.config.QueueNames;
import microservices.common.config.RoutingKeyNames;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConfig {

    @Bean(name = "createPriceQueue")
    public Queue createQueue() {
        return new Queue(QueueNames.PRICE_CREATE_QUEUE, false);
    }

    @Bean(name = "deletePriceQueue")
    public Queue deleteQueue() {
        return new Queue(QueueNames.PRICE_DELETE_QUEUE, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(ExchangeNames.PRICE_EXCHANGE);
    }

    @Bean
    public Binding bindingCreateListener(@Qualifier("createPriceQueue") Queue queue,
                                         TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(RoutingKeyNames.PRICE_CREATE_KEY);
    }

    @Bean
    public Binding bindingDeleteListener(@Qualifier("deletePriceQueue") Queue queue,
                                         TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(RoutingKeyNames.PRICE_DELETE_KEY);
    }

}

