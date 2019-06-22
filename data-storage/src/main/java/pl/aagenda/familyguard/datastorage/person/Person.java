package pl.aagenda.familyguard.datastorage.person;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import pl.aagenda.familyguard.datastorage.artifact.Artifact;
import pl.aagenda.familyguard.datastorage.event.Event;
import pl.aagenda.familyguard.datastorage.resource.Resource;

import java.io.Serializable;
import java.util.List;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.Person.CHILD_OF;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.Person.FATHER_OF;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.Person.MOTHER_OF;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.Person.PARTICIPATES;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.Person.SPOUSE_OF;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.Person.WIELDS;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.RELATES;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.Resource.CONTAINS_PERSON;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id", "name", "sex"})
@ToString(of = {"id", "name", "sex"})
@NodeEntity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person implements Serializable {
    private static final long serialVersionUID = -8395078083306934785L;

    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String name;

    @Property
    private Sex sex;

    @Relationship(type = FATHER_OF, direction = INCOMING)
    private Person father;

    @Relationship(type = MOTHER_OF, direction = INCOMING)
    private Person mother;

    @Relationship(type = SPOUSE_OF, direction = INCOMING)
    private List<Person> spouses;

    @Relationship(type = CHILD_OF, direction = INCOMING)
    private List<Person> children;

    @Relationship(type = RELATES)
    private List<RelativePerson> relatives;

    @Relationship(type = WIELDS)
    private List<Artifact> artifacts;

    @Relationship(type = PARTICIPATES)
    private List<Event> events;

    @Relationship(type = CONTAINS_PERSON, direction = INCOMING)
    private List<Resource> resources;
}
