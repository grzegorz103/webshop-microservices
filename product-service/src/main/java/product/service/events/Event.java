package product.service.events;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Event<T extends Serializable> {

    private EventInfo<T> eventInfo;

    private String exchange;

    private String routingKey;

}
