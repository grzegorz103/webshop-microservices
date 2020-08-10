package service.event.api;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.event.models.EventInfo;
import service.event.service.EventService;

import java.util.Collection;

@RestController
@RequestMapping("${url.events}")
@CrossOrigin
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

    @GetMapping("/report")
    public ResponseEntity<InputStreamResource> eventReport() {
        return ResponseEntity
                .ok()
                .header("Content-Disposition", "inline; filename=event-raport.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(GeneratePdf.generate(eventService.getAll())));
    }

}
