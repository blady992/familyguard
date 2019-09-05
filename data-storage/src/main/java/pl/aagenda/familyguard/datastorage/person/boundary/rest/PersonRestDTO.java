package pl.aagenda.familyguard.datastorage.person.boundary.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import pl.aagenda.familyguard.datastorage.event.boundary.rest.EventMinimalRestDTO;
import pl.aagenda.familyguard.datastorage.person.entity.Sex;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class PersonRestDTO {
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    private Sex sex;

    @Valid
    private PersonMinimalRestDTO father;

    @Valid
    private PersonMinimalRestDTO mother;

    @Valid
    private List<PersonMinimalRestDTO> children;
    private List<EventMinimalRestDTO> events;
}
