package service.event.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
public class EventInfo<T> implements Serializable {

    private T message;

    private Instant creationDate;

}
