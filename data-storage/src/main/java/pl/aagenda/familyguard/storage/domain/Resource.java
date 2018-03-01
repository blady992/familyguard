package pl.aagenda.familyguard.storage.domain;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.io.Serializable;
import java.util.Set;

import static org.neo4j.ogm.annotation.Relationship.UNDIRECTED;

@Data
@NodeEntity
public class Resource implements Serializable {
    private static final long serialVersionUID = 5083768405609885557L;

    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = "CONTAINS_PERSON")
    private Set<Person> people;

    @Relationship(type = "CONTAINS_ARTIFACT")
    private Set<Artifact> artifacts;

    @Relationship(type = "CONTAINS_EVENT")
    private Set<Event> events;

    @Relationship(type = "RELATES", direction = UNDIRECTED)
    private Set<Resource> relatedResources;
}
