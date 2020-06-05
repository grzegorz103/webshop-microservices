package service.event.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import service.event.models.EventInfo;

import java.util.Objects;

@RabbitListener(queues = "spring-boot")
@Component
@Slf4j
public class EventListener {

    private final ObjectMapper objectMapper;

    public EventListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitHandler
    @SuppressWarnings("unchecked")
    public void receiveMessage(String message) {
        try {
            EventInfo<String> in = objectMapper.readValue(message, EventInfo.class);
            log.info(Objects.requireNonNull(in).getMessage());
        } catch (JsonProcessingException e) {
         log.error(e.getMessage());
        }
    }

}
