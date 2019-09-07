package pl.aagenda.familyguard.datastorage.event.boundary.rest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.aagenda.familyguard.datastorage.common.mapstruct.CommonMapperConfig;
import pl.aagenda.familyguard.datastorage.event.entity.EventEntity;
import pl.aagenda.familyguard.datastorage.person.boundary.rest.PersonRestMapper;

@Mapper(config = CommonMapperConfig.class, uses = PersonRestMapper.class)
public interface EventRestMapper {
    EventRestDTO toDto(EventEntity entity);

    EventMinimalRestDTO toMinimalDto(EventEntity entity);

    EventEntity toEntity(EventRestDTO dto);

    @Mapping(target = "participants", ignore = true)
    @Mapping(target = "location", ignore = true)
    @Mapping(target = "description", ignore = true)
    EventEntity toEntity(EventMinimalRestDTO dto);
}
