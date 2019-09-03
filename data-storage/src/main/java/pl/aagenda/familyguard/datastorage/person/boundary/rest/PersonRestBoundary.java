package pl.aagenda.familyguard.datastorage.person.boundary.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.aagenda.familyguard.datastorage.person.boundary.PersonBoundary;
import pl.aagenda.familyguard.datastorage.person.control.PersonControl;
import pl.aagenda.familyguard.datastorage.person.entity.PersonEntity;

import javax.validation.Valid;

import static pl.aagenda.familyguard.datastorage.constants.ResourcePath.Api.API_V1_PATH;
import static pl.aagenda.familyguard.datastorage.constants.ResourcePath.ID_PATH_VARIABLE;
import static pl.aagenda.familyguard.datastorage.constants.ResourcePath.PEOPLE_PATH;

@RestController
@RequestMapping(path = API_V1_PATH + PEOPLE_PATH)
@RequiredArgsConstructor
public class PersonRestBoundary implements PersonBoundary {
    private final PersonControl personControl;
    private final PersonRestMapper mapper;

    @GetMapping
    public Page<PersonRestDTO> getPeopleDtos(@PageableDefault Pageable pageable) {
        return getPeople(pageable)
                .map(mapper::toDto);
    }

    @GetMapping(ID_PATH_VARIABLE)
    public PersonRestDTO getPersonDto(@PathVariable Long id) {
        return mapper.toDto(getPerson(id));
    }

    @PostMapping
    public PersonRestDTO savePerson(@RequestBody @Valid PersonRestDTO person) {
        return mapper.toDto(
                savePerson(mapper.toEntity(person)));
    }

    @DeleteMapping(ID_PATH_VARIABLE)
    public void delete(@PathVariable Long id) {
        deletePerson(id);
    }

    @Override
    public Page<PersonEntity> getPeople(Pageable pageable) {
        return personControl.getPeople(pageable);
    }

    @Override
    public PersonEntity getPerson(long id) {
        return personControl.getPerson(id);
    }

    @Override
    public PersonEntity savePerson(PersonEntity person) {
        return personControl.savePerson(person);
    }

    @Override
    public void deletePerson(long id) {
        personControl.deletePerson(id);
    }
}
