package pl.aagenda.familyguard.datastorage.person.entity.neo4j;

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
import pl.aagenda.familyguard.datastorage.event.entity.neo4j.Event;
import pl.aagenda.familyguard.datastorage.person.entity.Sex;
import pl.aagenda.familyguard.datastorage.resource.Resource;

import java.io.Serializable;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.neo4j.ogm.annotation.Relationship.INCOMING;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.Person.PARENT_OF;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.Person.PARTICIPATES;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.Person.WIELDS;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.Resource.CONTAINS_PERSON;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"name", "sex"})
@ToString(of = {"name", "sex"})
@NodeEntity
public class Person implements Serializable {
    private static final long serialVersionUID = 591218231051557148L;

    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String name;

    @Property
    private Sex sex;

    @Relationship(type = PARENT_OF, direction = INCOMING)
    @Builder.Default
    private List<Person> parents = newArrayList();

    @Relationship(type = PARENT_OF)
    @Builder.Default
    private List<Person> children = newArrayList();

    @Relationship(type = WIELDS)
    @Builder.Default
    private List<Artifact> artifacts = newArrayList();

    @Relationship(type = PARTICIPATES)
    @Builder.Default
    private List<Event> events = newArrayList();

    @Relationship(type = CONTAINS_PERSON, direction = INCOMING)
    @Builder.Default
    private List<Resource> resources = newArrayList();
}
