package pl.aagenda.familyguard.datastorage.person.boundary.rest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.aagenda.familyguard.datastorage.common.mapstruct.CommonMapperConfig;
import pl.aagenda.familyguard.datastorage.person.entity.PersonEntity;

@Mapper(config = CommonMapperConfig.class)
public interface PersonRestMapper {
    PersonRestDTO toDto(PersonEntity entity);

    PersonMinimalRestDTO toMinimalDto(PersonEntity entity);

    @Mapping(target = "resources", ignore = true)
    @Mapping(target = "events", ignore = true)
    @Mapping(target = "artifacts", ignore = true)
    PersonEntity toEntity(PersonRestDTO dto);

    @Mapping(target = "resources", ignore = true)
    @Mapping(target = "mother", ignore = true)
    @Mapping(target = "father", ignore = true)
    @Mapping(target = "events", ignore = true)
    @Mapping(target = "children", ignore = true)
    @Mapping(target = "artifacts", ignore = true)
    PersonEntity toEntity(PersonMinimalRestDTO dto);
}
