package price.service.events.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import microservices.common.events.EventInfo;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import price.service.mappers.PriceMapper;
import price.service.services.price.PriceDTO;
import price.service.services.price.PriceService;

import java.math.BigDecimal;

@RabbitListener(queues = "queue.price.create")
@Component
@Slf4j
public class PriceCreateListener {

    private final ObjectMapper objectMapper;

    private final PriceService priceService;

    private final PriceMapper priceMapper;

    public PriceCreateListener(ObjectMapper objectMapper,
                               PriceService priceService,
                               PriceMapper priceMapper) {
        this.objectMapper = objectMapper;
        this.priceService = priceService;
        this.priceMapper = priceMapper;
    }

    @RabbitHandler
    public Long receiveMessage(String message) {
        try {
            EventInfo<Long> in = objectMapper.readValue(message, new TypeReference<EventInfo<Long>>() {});
            log.info("Receive create price message");
            return priceService.create(new PriceDTO(null, BigDecimal.valueOf(in.getMessage()))).getId();
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        throw new IllegalStateException();
    }

}
