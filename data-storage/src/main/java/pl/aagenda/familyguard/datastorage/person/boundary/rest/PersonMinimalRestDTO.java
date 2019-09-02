package pl.aagenda.familyguard.datastorage.person.boundary.rest;

import lombok.Data;
import pl.aagenda.familyguard.datastorage.person.entity.Sex;

@Data
public class PersonMinimalRestDTO {
    private Long id;
    private String name;
    private Sex sex;
}
