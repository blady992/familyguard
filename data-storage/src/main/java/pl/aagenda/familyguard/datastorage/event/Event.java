package pl.aagenda.familyguard.datastorage.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import pl.aagenda.familyguard.datastorage.artifact.Artifact;
import pl.aagenda.familyguard.datastorage.person.entity.neo4j.Person;
import pl.aagenda.familyguard.datastorage.resource.Resource;

import java.io.Serializable;
import java.util.Set;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;
import static org.neo4j.ogm.annotation.Relationship.UNDIRECTED;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.Event.FEATURES;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.Person.PARTICIPATES;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.RELATES;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.Resource.CONTAINS_EVENT;

@Data
@EqualsAndHashCode(exclude = {"relatedEvents", "participants", "artifacts", "resources"})
@ToString(exclude = {"relatedEvents", "participants", "artifacts", "resources"})
@NodeEntity
public class Event implements Serializable {
    private static final long serialVersionUID = 4136183313750879131L;

    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = RELATES, direction = UNDIRECTED)
    private Set<RelativeEvent> relatedEvents;

    @Relationship(type = PARTICIPATES, direction = INCOMING)
    private Set<Person> participants;

    @Relationship(type = FEATURES)
    private Set<Artifact> artifacts;

    @Relationship(type = CONTAINS_EVENT, direction = INCOMING)
    private Set<Resource> resources;
}
