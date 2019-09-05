package pl.aagenda.familyguard.datastorage.event.control.neo4j;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.aagenda.familyguard.datastorage.common.mapstruct.CyclicGraphContext;
import pl.aagenda.familyguard.datastorage.event.control.EventControl;
import pl.aagenda.familyguard.datastorage.event.entity.EventEntity;
import pl.aagenda.familyguard.datastorage.event.entity.neo4j.Event;

@RequiredArgsConstructor
public class Neo4jRepositoryEventControl implements EventControl {
    private final EventNeo4jRepository repository;
    private final Neo4jEventMapper mapper;

    @Override
    public EventEntity saveEvent(EventEntity eventEntity) {
        Event event = mapper.toEvent(eventEntity, new CyclicGraphContext());
        event = repository.save(event);
        return mapper.toEventEntity(event, new CyclicGraphContext());
    }

    @Override
    public Page<EventEntity> getEvents(Pageable pageable) {
        return repository.findAll(pageable, 1)
                .map(event -> mapper.toEventEntity(event, new CyclicGraphContext()));
    }

    @Override
    public EventEntity getEvent(long id) {
        return repository.findById(id, 1)
                .map(event -> mapper.toEventEntity(event, new CyclicGraphContext()))
                .orElse(null);
    }

    @Override
    public void deleteEvent(long id) {
        repository.deleteById(id);
    }

    @Override
    public void addParticipant(long eventId, long participantId) {
        repository.addParticipant(eventId, participantId);
    }

    @Override
    public void removeParticipant(long eventId, long participantId) {
        repository.removeParticipant(eventId, participantId);
    }
}
