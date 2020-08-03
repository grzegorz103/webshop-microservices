package order.service.events;

import microservices.common.events.EventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import microservices.common.events.Event;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Override
    public Object publishAndReceive(Event<? extends Serializable> eventEntity) {
        try {
            return rabbitTemplate.convertSendAndReceive(
                    eventEntity.getExchange(),
                    eventEntity.getRoutingKey(),
                    objectMapper.writeValueAsString(eventEntity.getEventInfo())
            );
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }

        throw new IllegalStateException();
    }

}

