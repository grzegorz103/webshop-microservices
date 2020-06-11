package price.service.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import price.service.mappers.PriceMapper;
import price.service.services.price.PriceService;

@RabbitListener(queues = "spring-boot")
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
    public void receiveMessage(String message) {
        try {
            EventInfo<PriceCreateRecvInfo> in = objectMapper.readValue(message, new TypeReference<EventInfo<PriceCreateRecvInfo>>() {});
            priceService.create(priceMapper.toDTO(in.getMessage()));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }

}
