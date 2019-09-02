package pl.aagenda.familyguard.datastorage.person.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.aagenda.familyguard.datastorage.artifact.Artifact;
import pl.aagenda.familyguard.datastorage.event.Event;
import pl.aagenda.familyguard.datastorage.resource.Resource;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity implements Serializable {
    protected static final long serialVersionUID = -7019386139389493773L;

    protected Long id;

    protected String name;

    protected Sex sex;

    protected Map<String, List<PersonEntity>> relatives;

    protected List<Artifact> artifacts;

    protected List<Event> events;

    protected List<Resource> resources;
}
