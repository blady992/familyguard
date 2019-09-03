package pl.aagenda.familyguard.datastorage.event.boundary.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.aagenda.familyguard.datastorage.event.entity.Location;
import pl.aagenda.familyguard.datastorage.person.boundary.rest.PersonMinimalRestDTO;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventRestDTO {
    protected Long id;

    protected String name;

    protected String description;

    protected Location location;

    protected List<PersonMinimalRestDTO> participants;
}
