package pl.aagenda.familyguard.datastorage.event.control.neo4j;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.aagenda.familyguard.datastorage.event.entity.neo4j.Event;

@Repository
public interface EventNeo4jRepository extends Neo4jRepository<Event, Long> {
    @Query("MATCH (person:Person)-[p:PARTICIPATES]->(event:Event) " +
            "WHERE id(person)={participantId} AND id(event)={eventId} " +
            "DELETE p")
    void removeParticipant(@Param("eventId") Long eventId, @Param("participantId") Long participantId);

    @Query("MATCH (participant:Person), (event:Event) " +
            "WHERE id(participant) = {participantId} AND id(event) = {eventId} " +
            "CREATE (participant)-[r:PARTICIPATES]->(event)")
    void addParticipant(@Param("eventId") Long eventId, @Param("participantId") Long participantId);
}
