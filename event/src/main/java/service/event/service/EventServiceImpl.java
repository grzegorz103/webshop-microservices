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

    @Autowired
    private EventStateProvider eventStateProvider;

    @Override
    public EventInfo<? extends Serializable> add(EventInfo<? extends Serializable> event) {
        Objects.requireNonNull(event);
        return null;
    }

    @Override
    public Collection<?> getAll() {
        return null;
    }
}
