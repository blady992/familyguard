package pl.aagenda.familyguard.datastorage.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

import static java.lang.String.format;
import static org.neo4j.ogm.annotation.Relationship.INCOMING;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.Person.CHILD_OF;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.Person.PARTICIPATES;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.Person.SPOUSE_OF;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.Person.WIELDS;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.RELATES;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.Resource.CONTAINS_PERSON;
import static pl.aagenda.familyguard.datastorage.person.Sex.FEMALE;
import static pl.aagenda.familyguard.datastorage.person.Sex.MALE;

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

    @Relationship(type = CHILD_OF)
    @JsonIgnore
    private List<Person> parents;

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

    public Person getFather() {
        return parents.stream()
                .filter(person -> MALE == person.sex)
                .reduce((father1, father2) -> {
                    throw new IllegalStateException(format(
                            "Person %d links to more than 1 father (%d, %d)", id, father1.id, father2.id));
                })
                .orElse(null);
    }

    public void setFather(Person father) {
        parents.removeIf(person -> MALE == person.sex);
        parents.add(father);
    }

    public Person getMother() {
        return parents.stream()
                .filter(person -> FEMALE == person.sex)
                .reduce((mother1, mother2) -> {
                    throw new IllegalStateException(format(
                            "Person %d links to more than 1 mother (%d, %d)", id, mother1.id, mother2.id));
                })
                .orElse(null);
    }

    public void setMother(Person mother) {
        parents.removeIf(person -> FEMALE == person.sex);
        parents.add(mother);
    }
}
