package pl.aagenda.familyguard.datastorage.event.control;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import pl.aagenda.familyguard.datastorage.event.entity.EventEntity;

@RequiredArgsConstructor
@Transactional
public class TransactionalEventControlDecorator implements EventControl {
    private final EventControl delegate;

    @Override
    public EventEntity saveEvent(EventEntity event) {
        return delegate.saveEvent(event);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EventEntity> getEvents(Pageable pageable) {
        return delegate.getEvents(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public EventEntity getEvent(long id) {
        return delegate.getEvent(id);
    }

    @Override
    public void deleteEvent(long id) {
        delegate.deleteEvent(id);
    }

    @Override
    public void addParticipant(long eventId, long participantId) {
        delegate.addParticipant(eventId, participantId);
    }

    @Override
    public void removeParticipant(long eventId, long participantId) {
        delegate.removeParticipant(eventId, participantId);
    }
}
