package pl.aagenda.familyguard.datastorage.service;

import lombok.RequiredArgsConstructor;
import org.neo4j.ogm.exception.core.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.aagenda.familyguard.datastorage.domain.node.Event;
import pl.aagenda.familyguard.datastorage.repository.EventRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    @Transactional(readOnly = true)
    public Event getEvent(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No event with id " + id + " found"));
    }

    @Transactional(readOnly = true)
    public Page<Event> getAllEvents(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    public Event saveEvent(Event artifact) {
        return eventRepository.save(artifact);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
