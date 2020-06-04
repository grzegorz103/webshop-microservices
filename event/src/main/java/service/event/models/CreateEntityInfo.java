package service.event.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateEntityInfo<T> implements Serializable {

    private T message;

}
