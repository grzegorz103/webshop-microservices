package service.event.models;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class EventInfo<T> implements Serializable {

    private T message;

    private Instant creationDate;

}
