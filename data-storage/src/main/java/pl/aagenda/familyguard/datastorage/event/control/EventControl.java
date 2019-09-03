package pl.aagenda.familyguard.datastorage.event.control;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.aagenda.familyguard.datastorage.event.entity.EventEntity;

public interface EventControl {
    EventEntity saveEvent(EventEntity event);

    Page<EventEntity> getEvents(Pageable pageable);

    EventEntity getEvent(long id);

    void deleteEvent(long id);
}
