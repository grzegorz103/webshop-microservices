package product.service.events;

import microservices.common.events.Event;
import org.springframework.amqp.core.Message;

import java.io.Serializable;

public interface EventPublisher {

    void publish(Event<? extends Serializable> eventEntity);

    Object publishAndReceive(Event<? extends Serializable> eventEntity);

}
