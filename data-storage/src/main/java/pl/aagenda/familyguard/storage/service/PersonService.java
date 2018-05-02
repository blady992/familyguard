package pl.aagenda.familyguard.storage.service;

import lombok.RequiredArgsConstructor;
import org.neo4j.ogm.exception.core.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.aagenda.familyguard.storage.domain.node.Person;
import pl.aagenda.familyguard.storage.repository.PersonRepository;

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
        return personRepository.save(person);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
