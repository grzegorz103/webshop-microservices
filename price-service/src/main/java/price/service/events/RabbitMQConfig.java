package price.service.events;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConfig {

    public static final String topicExchangeName = "price-exchange";

    public static final String createQueueName = "queue.price.create";

    public static final String deleteQueueName = "queue.price.delete";

    @Bean(name = "createPriceQueue")
    public Queue createQueue() {
        return new Queue(createQueueName, false);
    }

    @Bean(name = "deletePriceQueue")
    public Queue deleteQueue() {
        return new Queue(deleteQueueName, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    public Binding bindingCreateListener(@Qualifier("createPriceQueue") Queue queue,
                                         TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("createPriceKey");
    }

    @Bean
    public Binding bindingDeleteListener(@Qualifier("deletePriceQueue") Queue queue,
                                         TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("deletePriceKey");
    }

}

