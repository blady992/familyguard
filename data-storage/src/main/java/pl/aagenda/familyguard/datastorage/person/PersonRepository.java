package pl.aagenda.familyguard.datastorage.person;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends Neo4jRepository <Person, Long> {
    Page<Person> findAllBySex(Sex sex, Pageable pageable);
}
