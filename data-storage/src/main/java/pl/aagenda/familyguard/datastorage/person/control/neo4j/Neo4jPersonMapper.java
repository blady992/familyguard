package pl.aagenda.familyguard.datastorage.person.control.neo4j;

import pl.aagenda.familyguard.datastorage.person.entity.PersonEntity;
import pl.aagenda.familyguard.datastorage.person.entity.neo4j.Person;

public interface Neo4jPersonMapper {
    Person toPerson(PersonEntity personEntity);

    PersonEntity toPersonEntity(Person person);
}
