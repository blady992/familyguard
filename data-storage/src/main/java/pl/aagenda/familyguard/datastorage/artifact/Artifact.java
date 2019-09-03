package pl.aagenda.familyguard.datastorage.artifact;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import pl.aagenda.familyguard.datastorage.event.Event;
import pl.aagenda.familyguard.datastorage.person.entity.neo4j.Person;
import pl.aagenda.familyguard.datastorage.resource.Resource;

import java.io.Serializable;
import java.util.Set;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;
import static org.neo4j.ogm.annotation.Relationship.UNDIRECTED;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.Event.FEATURES;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.Person.WIELDS;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.RELATES;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.Resource.CONTAINS_ARTIFACT;

@Data
@EqualsAndHashCode(exclude = {"relatedArtifacts", "people", "events", "resources"})
@ToString(exclude = {"relatedArtifacts", "people", "events", "resources"})
@NodeEntity
public class Artifact implements Serializable {
    private static final long serialVersionUID = 7867932069639327366L;

    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = RELATES, direction = UNDIRECTED)
    private Set<RelativeArtifact> relatedArtifacts;

    @Relationship(type = WIELDS, direction = INCOMING)
    private Set<Person> people;

    @Relationship(type = FEATURES, direction = INCOMING)
    private Set<Event> events;

    @Relationship(type = CONTAINS_ARTIFACT, direction = INCOMING)
    private Set<Resource> resources;
}
