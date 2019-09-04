package pl.aagenda.familyguard.datastorage.event.boundary.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.aagenda.familyguard.datastorage.event.boundary.EventBoundary;
import pl.aagenda.familyguard.datastorage.event.control.EventControl;
import pl.aagenda.familyguard.datastorage.event.entity.EventEntity;

import javax.validation.Valid;

import static pl.aagenda.familyguard.datastorage.constants.ResourcePath.Api.API_V1_PATH;
import static pl.aagenda.familyguard.datastorage.constants.ResourcePath.EVENTS_PATH;
import static pl.aagenda.familyguard.datastorage.constants.ResourcePath.ID_PATH_VARIABLE;

@RestController
@RequestMapping(path = API_V1_PATH + EVENTS_PATH)
@RequiredArgsConstructor
public class EventRestBoundary implements EventBoundary {
    private final EventControl eventControl;
    private final EventRestMapper mapper;

    @GetMapping
    public Page<EventRestDTO> getEventDtos(@PageableDefault Pageable pageable) {
        return getEvents(pageable)
                .map(mapper::toDto);
    }

    @GetMapping(ID_PATH_VARIABLE)
    public EventRestDTO getEventDto(@PathVariable Long id) {
        return mapper.toDto(
                getEvent(id));
    }

    @PostMapping
    public EventRestDTO saveEvent(@Valid @RequestBody EventRestDTO dto) {
        return mapper.toDto(
                saveEvent(mapper.toEntity(dto)));
    }

    @DeleteMapping(ID_PATH_VARIABLE)
    public void delete(@PathVariable Long id) {
        deleteEvent(id);
    }

    @Override
    public EventEntity saveEvent(EventEntity event) {
        return eventControl.saveEvent(event);
    }

    @Override
    public Page<EventEntity> getEvents(Pageable pageable) {
        return eventControl.getEvents(pageable);
    }

    @Override
    public EventEntity getEvent(long id) {
        return eventControl.getEvent(id);
    }

    @Override
    public void deleteEvent(long id) {
        eventControl.deleteEvent(id);
    }
}
