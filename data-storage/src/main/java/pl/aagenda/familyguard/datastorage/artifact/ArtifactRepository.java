package pl.aagenda.familyguard.datastorage.artifact;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtifactRepository extends Neo4jRepository<Artifact, Long> {
}
