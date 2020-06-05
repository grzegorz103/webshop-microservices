package product.service.events;

import org.springframework.stereotype.Component;

import java.io.Serializable;

public class EventFactory {

    public static <T extends Serializable> Event<T> create(T message, String exchange, String routingKey) {
        return new Event<>(new EventInfo<>(message), exchange, routingKey);
    }

}
