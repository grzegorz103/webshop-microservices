package microservices.common.events;

import java.io.Serializable;

public interface EventPublisher {

    void publish(Event<? extends Serializable> eventEntity);

    Object publishAndReceive(Event<? extends Serializable> eventEntity);

}
