package pl.aagenda.familyguard.storage.domain.relationship;

import lombok.Data;
import org.neo4j.ogm.annotation.*;
import pl.aagenda.familyguard.storage.domain.node.Person;

@Data
@RelationshipEntity(type = "RELATES")
public class Relative {
    @Id
    @GeneratedValue
    private Long relationshipId;

    @Property
    private RelativeRole role;

    @StartNode
    private Person person;

    @EndNode
    private Person relative;

    public enum RelativeRole {
        PARENT;
    }
}
