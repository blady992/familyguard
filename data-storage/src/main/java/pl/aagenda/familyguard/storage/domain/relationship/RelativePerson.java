package pl.aagenda.familyguard.storage.domain.relationship;

import lombok.Data;
import org.neo4j.ogm.annotation.*;
import pl.aagenda.familyguard.storage.domain.node.Person;

import javax.validation.constraints.NotNull;

import static pl.aagenda.familyguard.storage.constants.RelationshipConstants.RELATES;

@Data
@RelationshipEntity(type = RELATES)
public class RelativePerson {
    @Id
    @GeneratedValue
    private Long relationshipId;

    @Property
    @NotNull
    private RelativeRole role;

    @Property
    private String comment;

    @StartNode
    private Person root;

    @EndNode
    private Person relative;

    public enum RelativeRole {
        FATHER_OF, MOTHER_OF, CHILD_OF, HUSBAND_OF, WIFE_OF
    }
}
