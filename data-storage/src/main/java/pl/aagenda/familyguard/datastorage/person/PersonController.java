package pl.aagenda.familyguard.datastorage.person;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static pl.aagenda.familyguard.datastorage.constants.ResourcePath.Api.API_V1_PATH;
import static pl.aagenda.familyguard.datastorage.constants.ResourcePath.*;

@RestController
@RequestMapping(path = API_V1_PATH + PEOPLE_PATH)
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping(ID_PATH_VARIABLE)
    public Person getPerson(@PathVariable Long id) {
        return personService.getPerson(id);
    }

    @GetMapping
    public Page<Person> getPeople(@PageableDefault Pageable pageable) {
        return personService.getAllPeople(pageable);
    }

    @GetMapping(params = SEX_QUERY_PARAM)
    public List<Person> getPeople(@PageableDefault Pageable pageable, @RequestParam(SEX_QUERY_PARAM) String sex) {
        return Sex.fromString(sex)
                .map(personService::getAllPeopleBySex)
                .orElseThrow(() -> new IllegalArgumentException("Illegal sex value passed: \"" + sex + "\""));
    }

    @PostMapping
    public Person savePerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }

    @DeleteMapping(ID_PATH_VARIABLE)
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }
}
