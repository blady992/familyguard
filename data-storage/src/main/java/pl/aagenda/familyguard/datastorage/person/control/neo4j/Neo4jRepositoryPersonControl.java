package pl.aagenda.familyguard.datastorage.person.control.neo4j;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.aagenda.familyguard.datastorage.common.mapstruct.CyclicGraphContext;
import pl.aagenda.familyguard.datastorage.person.control.PersonControl;
import pl.aagenda.familyguard.datastorage.person.entity.PersonEntity;
import pl.aagenda.familyguard.datastorage.person.entity.neo4j.Person;

@RequiredArgsConstructor
public class Neo4jRepositoryPersonControl implements PersonControl {

    private final PersonNeo4jRepository repository;
    private final Neo4jPersonMapper mapper;

    @Override
    public PersonEntity savePerson(PersonEntity personEntity) {
        Person person = mapper.toPerson(personEntity, new CyclicGraphContext());
        person = repository.save(person, 0);
        return mapper.toPersonEntity(person, new CyclicGraphContext());
    }

    @Override
    public Page<PersonEntity> getPeople(Pageable pageable) {
        return repository.findAll(pageable, 1)
                .map(person -> mapper.toPersonEntity(person, new CyclicGraphContext()));
    }

    @Override
    public PersonEntity getPerson(long id) {
        return repository.findById(id, 1)
                .map(person -> mapper.toPersonEntity(person, new CyclicGraphContext()))
                .orElse(null);
    }

    @Override
    public void deletePerson(long id) {
        repository.deleteById(id);
    }
}
