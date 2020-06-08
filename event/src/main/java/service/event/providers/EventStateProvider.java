package service.event.providers;

import service.event.models.EventInfo;

import java.io.Serializable;
import java.util.Collection;

public interface EventStateProvider {

    EventInfo<? extends Serializable> add(EventInfo<? extends Serializable> event);

    Collection<?> getAll();

}
