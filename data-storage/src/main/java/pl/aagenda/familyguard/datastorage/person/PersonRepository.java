package pl.aagenda.familyguard.datastorage.person;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends Neo4jRepository <Person, Long> {
    List<Person> findAllBySex(Sex sex);
}
