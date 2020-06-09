package service.event.providers;

import service.event.models.EventInfo;

import java.io.Serializable;
import java.util.Collection;
import java.util.function.Predicate;

public interface EventStateProvider {

    EventInfo<? extends Serializable> add(EventInfo<? extends Serializable> event);

    Collection<EventInfo<?>> getAll();

    void deleteAll();

    void deleteIf(Predicate<EventInfo<?>> predicate);

}
