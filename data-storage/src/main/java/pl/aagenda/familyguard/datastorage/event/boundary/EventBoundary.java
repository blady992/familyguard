package pl.aagenda.familyguard.datastorage.event.boundary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.aagenda.familyguard.datastorage.event.entity.EventEntity;

public interface EventBoundary {
    EventEntity saveEvent(EventEntity event);

    Page<EventEntity> getEvents(Pageable pageable);

    EventEntity getEvent(long id);

    void deleteEvent(long id);

    void addParticipant(long eventId, long participantId);

    void removeParticipant(long eventId, long participantId);
}
