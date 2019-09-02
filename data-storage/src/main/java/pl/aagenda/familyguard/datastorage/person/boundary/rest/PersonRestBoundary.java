package pl.aagenda.familyguard.datastorage.person.boundary.rest;

import io.vavr.collection.Stream;
import lombok.RequiredArgsConstructor;
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
import java.util.List;

import static java.util.stream.Collectors.toList;
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
    public List<PersonRestDTO> getPeople(@PageableDefault Pageable pageable) {
        return Stream.ofAll(getPeople(pageable.getPageNumber(), pageable.getPageSize()))
                .map(mapper::toDto)
                .collect(toList());
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
    public void delete(Long id) {
        deletePerson(id);
    }

    @Override
    public Iterable<PersonEntity> getPeople(int pageNumber, int pageSize) {
        return personControl.getPeople(pageNumber, pageSize);
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
