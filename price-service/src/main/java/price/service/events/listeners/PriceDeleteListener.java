package price.service.events.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import price.service.events.EventInfo;
import price.service.services.price.PriceService;

@RabbitListener(queues = "queue.price.delete")
@Component
@Slf4j
public class PriceDeleteListener {

    private final PriceService priceService;

    private final ObjectMapper objectMapper;

    public PriceDeleteListener(PriceService priceService,
                               ObjectMapper objectMapper) {
        this.priceService = priceService;
        this.objectMapper = objectMapper;
    }

    @RabbitHandler
    public void receiveMessage(String message) {
        try {
            EventInfo<Long> in = objectMapper.readValue(message, new TypeReference<EventInfo<Long>>() {
            });
            log.info(String.format("Receive delete price message id %s", in.getMessage()));
            priceService.delete(in.getMessage());
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }

}
