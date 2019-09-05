package pl.aagenda.familyguard.datastorage.event.boundary.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.aagenda.familyguard.datastorage.event.entity.Location;
import pl.aagenda.familyguard.datastorage.person.boundary.rest.PersonMinimalRestDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventRestDTO {
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    private Location location;

    @Valid
    private List<PersonMinimalRestDTO> participants;
}
