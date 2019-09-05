package pl.aagenda.familyguard.datastorage.event.control.neo4j;

import pl.aagenda.familyguard.datastorage.common.mapstruct.CyclicGraphContext;
import pl.aagenda.familyguard.datastorage.event.entity.EventEntity;
import pl.aagenda.familyguard.datastorage.event.entity.neo4j.Event;

public interface Neo4jEventMapper {
    Event toEvent(EventEntity entity, CyclicGraphContext cyclicGraphContext);

    EventEntity toEventEntity(Event event, CyclicGraphContext cyclicGraphContext);
}
