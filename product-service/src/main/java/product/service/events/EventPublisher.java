package product.service.events;

import java.io.Serializable;

public interface EventPublisher {

    void publish(Event<? extends Serializable> eventEntity);

}
