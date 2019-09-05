package pl.aagenda.familyguard.datastorage.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import pl.aagenda.familyguard.datastorage.artifact.Artifact;
import pl.aagenda.familyguard.datastorage.person.entity.neo4j.Person;

import java.io.Serializable;
import java.util.Set;

import static org.neo4j.ogm.annotation.Relationship.UNDIRECTED;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.RELATES;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.Resource.CONTAINS_ARTIFACT;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.Resource.CONTAINS_PERSON;

@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@NodeEntity
public class Resource implements Serializable {
    private static final long serialVersionUID = 5083768405609885557L;

    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = CONTAINS_PERSON)
    private Set<Person> people;

    @Relationship(type = CONTAINS_ARTIFACT)
    private Set<Artifact> artifacts;

//    @Relationship(type = CONTAINS_EVENT)
//    private Set<Event> events;

    @Relationship(type = RELATES, direction = UNDIRECTED)
    private Set<Resource> resources;
}
