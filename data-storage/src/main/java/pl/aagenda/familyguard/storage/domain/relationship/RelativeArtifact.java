package pl.aagenda.familyguard.storage.domain.relationship;

import lombok.Data;
import org.neo4j.ogm.annotation.*;
import pl.aagenda.familyguard.storage.domain.node.Artifact;

import static pl.aagenda.familyguard.storage.constants.RelationshipConstants.RELATES;

@Data
@RelationshipEntity(type = RELATES)
public class RelativeArtifact {
    @Id
    @GeneratedValue
    private Long relationshipId;

    @StartNode
    private Artifact root;

    @EndNode
    private Artifact relative;
}
