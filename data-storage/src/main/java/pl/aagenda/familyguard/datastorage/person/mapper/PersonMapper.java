package pl.aagenda.familyguard.datastorage.person.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pl.aagenda.familyguard.datastorage.person.Person;
import pl.aagenda.familyguard.datastorage.person.dto.PersonDTO;
import pl.aagenda.familyguard.datastorage.person.dto.PersonMinimalDTO;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDTO toPersonDto(Person person);
    PersonMinimalDTO toPersonMinimalDto(Person person);

    Person toPerson(PersonMinimalDTO dto);
    void updatePerson(PersonMinimalDTO dto, @MappingTarget Person person);
}
