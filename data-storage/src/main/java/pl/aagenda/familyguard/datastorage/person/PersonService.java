package pl.aagenda.familyguard.datastorage.person;

import lombok.RequiredArgsConstructor;
import org.neo4j.ogm.exception.core.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    @Transactional(readOnly = true)
    public Person getPerson(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No person with id " + id + " found"));
    }

    @Transactional(readOnly = true)
    public Page<Person> getAllPeople(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    public Person savePerson(Person person) {
        return personRepository.save(person, 0);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    public Page<Person> getAllPeopleBySex(Sex sex, Pageable pageable) {
        return personRepository.findAllBySex(sex, pageable);
    }
}
