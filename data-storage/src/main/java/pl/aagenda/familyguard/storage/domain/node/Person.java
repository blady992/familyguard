package pl.aagenda.familyguard.storage.domain.node;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
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
import pl.aagenda.familyguard.storage.domain.relationship.RelativePerson;

import java.io.Serializable;
import java.util.Set;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;
import static pl.aagenda.familyguard.storage.constants.RelationshipConstants.Person.CHILD_OF;
import static pl.aagenda.familyguard.storage.constants.RelationshipConstants.Person.FATHER_OF;
import static pl.aagenda.familyguard.storage.constants.RelationshipConstants.Person.HUSBAND_OF;
import static pl.aagenda.familyguard.storage.constants.RelationshipConstants.Person.MOTHER_OF;
import static pl.aagenda.familyguard.storage.constants.RelationshipConstants.Person.PARTICIPATES;
import static pl.aagenda.familyguard.storage.constants.RelationshipConstants.Person.WIELDS;
import static pl.aagenda.familyguard.storage.constants.RelationshipConstants.Person.WIFE_OF;
import static pl.aagenda.familyguard.storage.constants.RelationshipConstants.RELATES;
import static pl.aagenda.familyguard.storage.constants.RelationshipConstants.Resource.CONTAINS_PERSON;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"relatives", "artifacts", "events", "resources", "father", "mother", "husbands", "wives"})
@ToString(exclude = {"relatives", "artifacts", "events", "resources", "father", "mother", "husbands", "wives"})
@NodeEntity
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "id", scope = Long.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person implements Serializable {
    private static final long serialVersionUID = 4381021751140116918L;

    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String name;

    @JsonIdentityReference(alwaysAsId=true)
    @Relationship(type = FATHER_OF, direction = INCOMING)
    private Person father;

    @JsonIdentityReference(alwaysAsId=true)
    @Relationship(type = MOTHER_OF, direction = INCOMING)
    private Person mother;

    @JsonIdentityReference(alwaysAsId=true)
    @Relationship(type = HUSBAND_OF, direction = INCOMING)
    private Set<Person> husbands;

    @JsonIdentityReference(alwaysAsId=true)
    @Relationship(type = WIFE_OF, direction = INCOMING)
    private Set<Person> wives;

    @JsonIdentityReference(alwaysAsId=true)
    @Relationship(type = CHILD_OF, direction = INCOMING)
    private Set<Person> children;

    @JsonIdentityReference(alwaysAsId=true)
    @Relationship(type = RELATES)
    private Set<RelativePerson> relatives;

    @JsonIdentityReference(alwaysAsId=true)
    @Relationship(type = WIELDS)
    private Set<Artifact> artifacts;

    @JsonIdentityReference(alwaysAsId=true)
    @Relationship(type = PARTICIPATES)
    private Set<Event> events;

    @JsonIdentityReference(alwaysAsId=true)
    @Relationship(type = CONTAINS_PERSON, direction = INCOMING)
    private Set<Resource> resources;
}
