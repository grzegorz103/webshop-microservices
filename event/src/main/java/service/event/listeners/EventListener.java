package service.event.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@RabbitListener(queues = "spring-boot")
@Component
@Slf4j
public class EventListener {

    @RabbitHandler
    public void receiveMessage(String message) {
        log.info(message);
    }

}
