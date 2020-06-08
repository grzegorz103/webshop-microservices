package service.event.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.event.models.EventInfo;
import service.event.providers.EventStateProvider;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Service
public class EventServiceImpl implements EventService {

    private final EventStateProvider eventStateProvider;

    public EventServiceImpl(EventStateProvider eventStateProvider) {
        this.eventStateProvider = eventStateProvider;
    }

    @Override
    public EventInfo<? extends Serializable> add(EventInfo<? extends Serializable> event) {
        Objects.requireNonNull(event);
        return eventStateProvider.add(event);
    }

    @Override
    public Collection<EventInfo<?>> getAll() {
        return eventStateProvider.getAll();
    }

    @Override
    public void deleteAll() {
        eventStateProvider.deleteAll();
    }
}
