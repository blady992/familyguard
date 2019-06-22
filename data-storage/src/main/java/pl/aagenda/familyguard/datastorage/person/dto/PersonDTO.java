package pl.aagenda.familyguard.datastorage.person.dto;

import lombok.Data;
import pl.aagenda.familyguard.datastorage.person.Sex;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
public class PersonDTO {
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    private Sex sex;

    @Valid
    private PersonMinimalDTO mother;

    @Valid
    private PersonMinimalDTO father;

    @Valid
    private Collection<PersonMinimalDTO> spouses;

    @Valid
    private Collection<PersonMinimalDTO> children;
}
