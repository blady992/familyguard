package pl.aagenda.familyguard.storage.domain.node;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import pl.aagenda.familyguard.storage.domain.relationship.RelativePerson;

import java.io.Serializable;
import java.util.Set;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;
import static org.neo4j.ogm.annotation.Relationship.UNDIRECTED;
import static pl.aagenda.familyguard.storage.constants.RelationshipConstants.*;

@Data
@EqualsAndHashCode(exclude = {"relatives", "artifacts", "events", "resources"})
@ToString(exclude = {"relatives", "artifacts", "events", "resources"})
@NodeEntity
public class Person implements Serializable {
    private static final long serialVersionUID = -2570626112545200L;

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Relationship(type = RELATES, direction = UNDIRECTED)
    private Set<RelativePerson> relatives;

    @Relationship(type = WIELDS)
    private Set<Artifact> artifacts;

    @Relationship(type = PARTICIPATES)
    private Set<Event> events;

    @Relationship(type = CONTAINS_PERSON, direction = INCOMING)
    private Set<Resource> resources;
}
