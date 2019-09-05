package pl.aagenda.familyguard.datastorage.common.mapstruct;

import org.mapstruct.MapperConfig;

import static org.mapstruct.ReportingPolicy.ERROR;

@MapperConfig(componentModel = "spring", unmappedTargetPolicy = ERROR)
public interface CommonMapperConfig {
}
