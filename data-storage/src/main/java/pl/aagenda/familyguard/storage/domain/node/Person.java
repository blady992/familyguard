package pl.aagenda.familyguard.storage.domain.node;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import lombok.*;
import org.neo4j.ogm.annotation.*;
import pl.aagenda.familyguard.storage.domain.relationship.RelativePerson;

import java.io.Serializable;
import java.util.Set;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;
import static pl.aagenda.familyguard.storage.constants.RelationshipConstants.Person.*;
import static pl.aagenda.familyguard.storage.constants.RelationshipConstants.RELATES;
import static pl.aagenda.familyguard.storage.constants.RelationshipConstants.Resource.CONTAINS_PERSON;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"relatives", "artifacts", "events", "resources", "father", "mother", "husbands", "wives"})
@ToString(exclude = {"relatives", "artifacts", "events", "resources", "father", "mother", "husbands", "wives"})
@NodeEntity
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "id")
public class Person implements Serializable {
    private static final long serialVersionUID = 4381021751140116918L;

    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String name;

    @Relationship(type = FATHER_OF, direction = INCOMING)
    private Person father;

    @Relationship(type = MOTHER_OF, direction = INCOMING)
    private Person mother;

    @Relationship(type = HUSBAND_OF, direction = INCOMING)
    private Set<Person> husbands;

    @Relationship(type = WIFE_OF, direction = INCOMING)
    private Set<Person> wives;

    @Relationship(type = CHILD_OF, direction = INCOMING)
    private Set<Person> children;

    @Relationship(type = RELATES)
    private Set<RelativePerson> relatives;

    @Relationship(type = WIELDS)
    private Set<Artifact> artifacts;

    @Relationship(type = PARTICIPATES)
    private Set<Event> events;

    @Relationship(type = CONTAINS_PERSON, direction = INCOMING)
    private Set<Resource> resources;
}
