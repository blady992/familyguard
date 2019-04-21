package pl.aagenda.familyguard.datastorage.person;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.RELATES;

@Data
@RelationshipEntity(type = RELATES)
public class RelativePerson {
    @Id
    @GeneratedValue
    private Long relationshipId;

    @Property
    private String comment;

    @StartNode
    private Person root;

    @EndNode
    private Person relative;
}
