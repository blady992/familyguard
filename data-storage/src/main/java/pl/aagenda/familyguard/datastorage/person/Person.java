package pl.aagenda.familyguard.datastorage.person;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
import java.util.Set;

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
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "id", scope = Long.class)
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

    @JsonIdentityReference(alwaysAsId = true)
    @JsonDeserialize(using = PersonDeserializer.class)
    @Relationship(type = FATHER_OF, direction = INCOMING)
    private Person father;

    @JsonIdentityReference(alwaysAsId = true)
    @JsonDeserialize(using = PersonDeserializer.class)
    @Relationship(type = MOTHER_OF, direction = INCOMING)
    private Person mother;

    @JsonIdentityReference(alwaysAsId = true)
    @JsonDeserialize(contentUsing = PersonDeserializer.class)
    @Relationship(type = SPOUSE_OF, direction = INCOMING)
    private Set<Person> spouses;

    @JsonIdentityReference(alwaysAsId = true)
    @JsonDeserialize(contentUsing = PersonDeserializer.class)
    @Relationship(type = CHILD_OF, direction = INCOMING)
    private Set<Person> children;

    @JsonIdentityReference(alwaysAsId = true)
    @Relationship(type = RELATES)
    private Set<RelativePerson> relatives;

    @JsonIdentityReference(alwaysAsId = true)
    @Relationship(type = WIELDS)
    private Set<Artifact> artifacts;

    @JsonIdentityReference(alwaysAsId = true)
    @Relationship(type = PARTICIPATES)
    private Set<Event> events;

    @JsonIdentityReference(alwaysAsId = true)
    @Relationship(type = CONTAINS_PERSON, direction = INCOMING)
    private Set<Resource> resources;
}
