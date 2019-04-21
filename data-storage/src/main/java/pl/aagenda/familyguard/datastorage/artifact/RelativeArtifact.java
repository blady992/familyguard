package pl.aagenda.familyguard.datastorage.artifact;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

import static pl.aagenda.familyguard.datastorage.constants.RelationshipConstants.RELATES;

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
