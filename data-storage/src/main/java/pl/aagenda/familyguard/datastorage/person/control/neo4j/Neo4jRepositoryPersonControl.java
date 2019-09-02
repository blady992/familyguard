package pl.aagenda.familyguard.datastorage.person.control.neo4j;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import pl.aagenda.familyguard.datastorage.person.control.PersonControl;
import pl.aagenda.familyguard.datastorage.person.entity.neo4j.Person;
import pl.aagenda.familyguard.datastorage.person.entity.PersonEntity;

@RequiredArgsConstructor
public class Neo4jRepositoryPersonControl implements PersonControl {

    private final PersonNeo4jRepository repository;
    private final Neo4jPersonMapper mapper;

    @Override
    public PersonEntity savePerson(PersonEntity personEntity) {
        Person person = mapper.toPerson(personEntity);
        person = repository.save(person);
        return mapper.toPersonEntity(person);
    }

    @Override
    public Iterable<PersonEntity> getPeople(int pageNumber, int pageSize) {
        return repository.findAll(new PageRequest(pageNumber, pageSize))
                .map(mapper::toPersonEntity);
    }

    @Override
    public PersonEntity getPerson(long id) {
        return repository.findById(id, 1)
                .map(mapper::toPersonEntity)
                .orElse(null);
    }

    @Override
    public void deletePerson(long id) {
        repository.deleteById(id);
    }
}
