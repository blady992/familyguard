package pl.aagenda.familyguard.datastorage.event.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.aagenda.familyguard.datastorage.person.entity.PersonEntity;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventEntity {
    protected Long id;

    protected String name;

    protected String description;

    protected Location location;

    protected List<PersonEntity> participants;
}
