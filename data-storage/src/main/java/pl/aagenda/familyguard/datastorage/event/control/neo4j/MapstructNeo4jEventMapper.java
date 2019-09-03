package pl.aagenda.familyguard.datastorage.event.control.neo4j;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.aagenda.familyguard.datastorage.common.mapstruct.CommonMapperConfig;
import pl.aagenda.familyguard.datastorage.common.mapstruct.CyclicGraphContext;
import pl.aagenda.familyguard.datastorage.event.entity.EventEntity;
import pl.aagenda.familyguard.datastorage.event.entity.Location;
import pl.aagenda.familyguard.datastorage.event.entity.neo4j.Event;
import pl.aagenda.familyguard.datastorage.person.control.neo4j.MapstructNeo4jPersonMapper;

@Mapper(
        config = CommonMapperConfig.class,
        uses = MapstructNeo4jPersonMapper.class
)
public abstract class MapstructNeo4jEventMapper implements Neo4jEventMapper {

    @Override
    @Mapping(target = "locationName", source = "location.name")
    @Mapping(target = "locationLongitude", source = "location.longitude")
    @Mapping(target = "locationLatitude", source = "location.latitude")
    public abstract Event toEvent(EventEntity entity, @Context CyclicGraphContext cyclicGraphContext);

    @Override
    @Mapping(target = "location", expression = "java(toLocation(event))")
    public abstract EventEntity toEventEntity(Event event, @Context CyclicGraphContext cyclicGraphContext);

    @Mapping(target = "name", source = "locationName")
    @Mapping(target = "longitude", source = "locationLongitude")
    @Mapping(target = "latitude", source = "locationLatitude")
    protected abstract Location toLocation(Event event);
}
