package pl.aagenda.familyguard.datastorage.person.boundary.rest;

import lombok.Data;
import pl.aagenda.familyguard.datastorage.person.entity.Sex;

import java.util.List;

@Data
public class PersonRestDTO {
    private Long id;
    private String name;
    private Sex sex;
    private PersonMinimalRestDTO father;
    private PersonMinimalRestDTO mother;
    private List<PersonMinimalRestDTO> children;
}
