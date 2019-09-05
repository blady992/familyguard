package pl.aagenda.familyguard.datastorage.event.entity.neo4j;

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
import pl.aagenda.familyguard.datastorage.person.entity.neo4j.Person;

import java.io.Serializable;
import java.util.List;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;
import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.Person.PARTICIPATES;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"name", "description"})
@ToString(of = {"name", "description"})
@NodeEntity
public class Event implements Serializable {
    private static final long serialVersionUID = -5764660391049646910L;

    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String name;

    @Property
    private String description;

    @Property
    private String locationName;

    @Property
    private Double locationLongitude;

    @Property
    private Double locationLatitude;

    @Relationship(type = PARTICIPATES, direction = INCOMING)
    private List<Person> participants;
}
