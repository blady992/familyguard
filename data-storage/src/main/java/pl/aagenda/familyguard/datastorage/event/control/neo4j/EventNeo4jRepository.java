package pl.aagenda.familyguard.datastorage.event.control.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import pl.aagenda.familyguard.datastorage.event.entity.neo4j.Event;

@Repository
public interface EventNeo4jRepository extends Neo4jRepository<Event, Long> {
}
