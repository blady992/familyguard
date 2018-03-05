package pl.aagenda.familyguard.storage.domain.node;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.io.Serializable;
import java.util.Set;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;
import static org.neo4j.ogm.annotation.Relationship.UNDIRECTED;

@Data
@NodeEntity
public class Event implements Serializable {
    private static final long serialVersionUID = 4136183313750879131L;

    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = "PARTICIPATES", direction = INCOMING)
    private Set<Person> participants;

    @Relationship(type = "FEATURES")
    private Set<Artifact> artifacts;

    @Relationship(type = "RELATES", direction = UNDIRECTED)
    private Set<Event> relatedEvents;

    @Relationship(type = "CONTAINS_EVENT", direction = INCOMING)
    private Set<Resource> resources;
}
