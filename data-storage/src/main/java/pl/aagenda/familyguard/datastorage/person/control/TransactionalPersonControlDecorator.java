package pl.aagenda.familyguard.datastorage.person.control;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import pl.aagenda.familyguard.datastorage.person.entity.PersonEntity;

@RequiredArgsConstructor
@Transactional
public class TransactionalPersonControlDecorator implements PersonControl {
    private final PersonControl delegate;

    @Override
    public PersonEntity savePerson(PersonEntity person) {
        return delegate.savePerson(person);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PersonEntity> getPeople(Pageable pageable) {
        return delegate.getPeople(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonEntity getPerson(long id) {
        return delegate.getPerson(id);
    }

    @Override
    public void deletePerson(long id) {
        delegate.deletePerson(id);
    }
}
