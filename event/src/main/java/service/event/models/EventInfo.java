package service.event.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class EventInfo<T> implements Serializable {

    private T message;

}
