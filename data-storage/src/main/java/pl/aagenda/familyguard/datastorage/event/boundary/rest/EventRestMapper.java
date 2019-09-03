package pl.aagenda.familyguard.datastorage.event.boundary.rest;

import org.mapstruct.Mapper;
import pl.aagenda.familyguard.datastorage.common.mapstruct.CommonMapperConfig;
import pl.aagenda.familyguard.datastorage.event.entity.EventEntity;
import pl.aagenda.familyguard.datastorage.person.boundary.rest.PersonRestMapper;

@Mapper(config = CommonMapperConfig.class, uses = PersonRestMapper.class)
public interface EventRestMapper {
    EventRestDTO toDto(EventEntity entity);

    EventEntity toEntity(EventRestDTO dto);
}
