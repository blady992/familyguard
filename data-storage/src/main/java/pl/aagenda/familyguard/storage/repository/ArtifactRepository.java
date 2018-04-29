package pl.aagenda.familyguard.storage.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import pl.aagenda.familyguard.storage.domain.node.Artifact;

@Repository
public interface ArtifactRepository extends Neo4jRepository<Artifact, Long> {
}
