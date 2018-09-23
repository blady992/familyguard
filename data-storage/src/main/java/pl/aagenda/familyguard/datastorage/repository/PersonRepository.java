package pl.aagenda.familyguard.datastorage.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import pl.aagenda.familyguard.datastorage.domain.Sex;
import pl.aagenda.familyguard.datastorage.domain.node.Person;

import java.util.List;

@Repository
public interface PersonRepository extends Neo4jRepository <Person, Long> {
    List<Person> findAllBySex(Sex sex);
}
