package service.event.providers;

import org.springframework.stereotype.Component;
import service.event.models.EventInfo;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Predicate;

@Component
public class EventStateProviderImpl implements EventStateProvider {

    private Collection<EventInfo<?>> events = new LinkedList<>();

    @Override
    public EventInfo<? extends Serializable> add(EventInfo<? extends Serializable> event) {
        events.add(event);
        return event;
    }

    @Override
    public Collection<EventInfo<?>> getAll() {
        return events;
    }

    @Override
    public void deleteAll() {
        events.clear();
    }

    @Override
    public void deleteIf(Predicate<EventInfo<?>> predicate) {
        events.removeIf(predicate);
    }

}
