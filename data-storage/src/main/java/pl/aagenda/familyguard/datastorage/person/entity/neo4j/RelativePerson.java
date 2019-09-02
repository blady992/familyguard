package pl.aagenda.familyguard.datastorage.person.entity.neo4j;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.RELATES;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RelationshipEntity(type = RELATES)
@EqualsAndHashCode(of = "name")
public class RelativePerson {
    @Id
    @GeneratedValue
    private Long relationshipId;

    @Property
    private String name;

    @Property
    private String comment;

    @StartNode
    private Person root;

    @EndNode
    private Person relative;
}
