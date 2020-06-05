package product.service.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import product.service.services.product.ProductDTO;

import java.io.Serializable;

@Component
@Slf4j
public class EventPublisherImpl implements EventPublisher {

    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;

    @Autowired
    public EventPublisherImpl(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publish(Event<? extends Serializable> eventEntity) {
        try {
            rabbitTemplate.convertAndSend(
                    eventEntity.getExchange(),
                    eventEntity.getRoutingKey(),
                    objectMapper.writeValueAsString(eventEntity.getEventInfo())
            );
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}
