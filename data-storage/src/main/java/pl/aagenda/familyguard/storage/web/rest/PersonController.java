package pl.aagenda.familyguard.storage.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import pl.aagenda.familyguard.storage.domain.node.Person;
import pl.aagenda.familyguard.storage.service.PersonService;

import static pl.aagenda.familyguard.storage.constants.ResourcePath.Api.API_V1_PATH;
import static pl.aagenda.familyguard.storage.constants.ResourcePath.ID_PATH_VARIABLE;
import static pl.aagenda.familyguard.storage.constants.ResourcePath.PEOPLE_PATH;

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

    @PostMapping
    public Person savePerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }

    @DeleteMapping(ID_PATH_VARIABLE)
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }
}
