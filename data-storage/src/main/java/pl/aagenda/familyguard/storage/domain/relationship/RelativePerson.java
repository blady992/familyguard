package pl.aagenda.familyguard.storage.domain.relationship;

import lombok.Data;
import org.neo4j.ogm.annotation.*;
import pl.aagenda.familyguard.storage.domain.node.Person;

import static pl.aagenda.familyguard.storage.constants.RelationshipConstants.RELATES;

@Data
@RelationshipEntity(type = RELATES)
public class RelativePerson {
    @Id
    @GeneratedValue
    private Long relationshipId;

    @Property
    private RelativeRole role;

    @StartNode
    private Person root;

    @EndNode
    private Person relative;

    public enum RelativeRole {
        PARENTHOOD, MARRIAGE
    }
}
