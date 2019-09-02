package pl.aagenda.familyguard.datastorage.person.control;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.aagenda.familyguard.datastorage.person.entity.PersonEntity;

public interface PersonControl {
    PersonEntity savePerson(PersonEntity person);

    Page<PersonEntity> getPeople(Pageable pageable);

    PersonEntity getPerson(long id);

    void deletePerson(long id);
}
