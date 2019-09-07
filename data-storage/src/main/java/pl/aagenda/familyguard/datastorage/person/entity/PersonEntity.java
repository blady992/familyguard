package pl.aagenda.familyguard.datastorage.person.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.aagenda.familyguard.datastorage.event.entity.EventEntity;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {

    protected Long id;

    protected String name;

    protected Sex sex;

    protected PersonEntity father;

    protected PersonEntity mother;

    @Builder.Default
    protected List<PersonEntity> children = newArrayList();

    @Builder.Default
    protected List<EventEntity> events = newArrayList();
}
