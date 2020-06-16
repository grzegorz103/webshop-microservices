package order.service.events;

import microservices.common.events.Event;
import microservices.common.events.EventInfo;

import java.io.Serializable;

public class EventFactory {

    public static <T extends Serializable> Event<T> create(T message, String exchange, String routingKey) {
        return new Event<>(new EventInfo<>(message), exchange, routingKey);
    }

}

