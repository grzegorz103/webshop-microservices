package service.event.service;

import service.event.models.EventInfo;

import java.io.Serializable;
import java.util.Collection;

public interface EventService {

    EventInfo<? extends Serializable> add(EventInfo<? extends Serializable> event);

    Collection<EventInfo<?>> getAll();

    void deleteAll();

}
