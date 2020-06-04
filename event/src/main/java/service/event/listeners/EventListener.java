package service.event.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.event.models.CreateEntityInfo;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

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
            CreateEntityInfo<String> in = objectMapper.readValue(message, CreateEntityInfo.class);
            log.info(Objects.requireNonNull(in).getMessage());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
