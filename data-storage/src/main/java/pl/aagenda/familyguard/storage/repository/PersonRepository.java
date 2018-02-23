package pl.aagenda.familyguard.storage.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import pl.aagenda.familyguard.storage.domain.Person;

public interface PersonRepository extends Neo4jRepository <Person, Long> {
}
