package pl.aagenda.familyguard.storage.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import pl.aagenda.familyguard.storage.domain.node.Event;
import pl.aagenda.familyguard.storage.service.EventService;

import static pl.aagenda.familyguard.storage.constants.ResourcePath.Api.API_V1_PATH;
import static pl.aagenda.familyguard.storage.constants.ResourcePath.EVENTS_PATH;
import static pl.aagenda.familyguard.storage.constants.ResourcePath.ID_PATH_VARIABLE;

@RestController
@RequestMapping(path = API_V1_PATH + EVENTS_PATH)
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping(ID_PATH_VARIABLE)
    public Event getEvent(@PathVariable Long id) {
        return eventService.getEvent(id);
    }

    @GetMapping
    public Page<Event> getEvents(@PageableDefault Pageable pageable) {
        return eventService.getAllEvents(pageable);
    }

    @PostMapping
    public Event saveEvent(@RequestBody Event event) {
        return eventService.saveEvent(event);
    }

    @DeleteMapping(ID_PATH_VARIABLE)
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}
