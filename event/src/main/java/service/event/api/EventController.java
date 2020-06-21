package service.event.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.event.models.EventInfo;
import service.event.service.EventService;

import java.util.Collection;

@RestController
@RequestMapping("${url.events}")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public Collection<EventInfo<?>> getAll() {
        return eventService.getAll();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        eventService.deleteAll();
    }

}
