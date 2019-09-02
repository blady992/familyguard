package pl.aagenda.familyguard.datastorage.person.control.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import pl.aagenda.familyguard.datastorage.person.entity.neo4j.Person;

@Repository
public interface PersonNeo4jRepository extends Neo4jRepository <Person, Long> {
}
