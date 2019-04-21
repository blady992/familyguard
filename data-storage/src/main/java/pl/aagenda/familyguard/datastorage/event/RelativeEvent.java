package pl.aagenda.familyguard.datastorage.event;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.RELATES;

@Data
@RelationshipEntity(type = RELATES)
public class RelativeEvent {
    @Id
    @GeneratedValue
    private Long relationshipId;

    @StartNode
    private Event root;

    @EndNode
    private Event relative;
}
