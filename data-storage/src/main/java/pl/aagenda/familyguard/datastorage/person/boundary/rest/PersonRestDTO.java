package pl.aagenda.familyguard.datastorage.person.boundary.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import pl.aagenda.familyguard.datastorage.person.entity.Sex;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class PersonRestDTO {
    private Long id;
    private String name;
    private Sex sex;
    private PersonMinimalRestDTO father;
    private PersonMinimalRestDTO mother;
    private List<PersonMinimalRestDTO> children;
}
