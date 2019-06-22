package pl.aagenda.familyguard.datastorage.person;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.aagenda.familyguard.datastorage.person.dto.PersonDTO;
import pl.aagenda.familyguard.datastorage.person.dto.PersonMinimalDTO;
import pl.aagenda.familyguard.datastorage.person.mapper.PersonMapper;

import javax.validation.Valid;

import static pl.aagenda.familyguard.datastorage.constants.ResourcePath.Api.API_V1_PATH;
import static pl.aagenda.familyguard.datastorage.constants.ResourcePath.ID_PATH_VARIABLE;
import static pl.aagenda.familyguard.datastorage.constants.ResourcePath.PEOPLE_PATH;
import static pl.aagenda.familyguard.datastorage.constants.ResourcePath.SEX_QUERY_PARAM;

@RestController
@RequestMapping(path = API_V1_PATH + PEOPLE_PATH)
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;
    private final PersonMapper personMapper;

    @GetMapping(ID_PATH_VARIABLE)
    public PersonDTO getPerson(@PathVariable Long id) {
        return personMapper.toPersonDto(personService.getPerson(id));
    }

    @GetMapping
    public Page<PersonMinimalDTO> getPeople(@PageableDefault Pageable pageable) {
        return personService.getAllPeople(pageable)
                .map(personMapper::toPersonMinimalDto);
    }

    @GetMapping(params = SEX_QUERY_PARAM)
    public Page<PersonMinimalDTO> getPeople(@PageableDefault Pageable pageable, @RequestParam(SEX_QUERY_PARAM) String sexQueryParam) {
        return Sex.fromString(sexQueryParam)
                .map(sex -> personService.getAllPeopleBySex(sex, pageable))
                .orElseThrow(() -> new IllegalArgumentException("Illegal sex value passed: \"" + sexQueryParam + "\""))
                .map(personMapper::toPersonMinimalDto);
    }

    @PostMapping
    public PersonDTO savePerson(@RequestBody @Valid PersonMinimalDTO dto) {
        return personMapper.toPersonDto(
                personService.savePerson(
                        personMapper.toPerson(dto)));
    }

    @DeleteMapping(ID_PATH_VARIABLE)
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }
}
