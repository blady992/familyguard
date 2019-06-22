package pl.aagenda.familyguard.datastorage.person.dto;

import lombok.Data;
import pl.aagenda.familyguard.datastorage.person.Sex;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PersonMinimalDTO {
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    private Sex sex;
}
