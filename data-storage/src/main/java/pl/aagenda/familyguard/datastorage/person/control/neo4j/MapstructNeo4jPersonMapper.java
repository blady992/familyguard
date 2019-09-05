package pl.aagenda.familyguard.datastorage.person.control.neo4j;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.aagenda.familyguard.datastorage.common.mapstruct.CommonMapperConfig;
import pl.aagenda.familyguard.datastorage.common.mapstruct.CyclicGraphContext;
import pl.aagenda.familyguard.datastorage.event.control.neo4j.MapstructNeo4jEventMapper;
import pl.aagenda.familyguard.datastorage.person.entity.PersonEntity;
import pl.aagenda.familyguard.datastorage.person.entity.neo4j.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static pl.aagenda.familyguard.datastorage.person.entity.Sex.FEMALE;
import static pl.aagenda.familyguard.datastorage.person.entity.Sex.MALE;

@Mapper(
        config = CommonMapperConfig.class,
        imports = {Arrays.class, Objects.class, Collectors.class, Collections.class, Optional.class},
        uses = MapstructNeo4jEventMapper.class
)
public abstract class MapstructNeo4jPersonMapper implements Neo4jPersonMapper {

    protected static final Predicate<Person> FATHER_PREDICATE = person -> person.getSex() == MALE;
    protected static final Predicate<Person> MOTHER_PREDICATE = person -> person.getSex() == FEMALE;

    @Override
    @Mapping(target = "parents", expression = "java(Arrays.asList(personEntity.getFather(), personEntity.getMother()).stream().filter(Objects::nonNull).map(parentPersonEntity -> toPerson(parentPersonEntity, cyclicGraphContext)).collect(Collectors.toList()))")
    public abstract Person toPerson(PersonEntity personEntity, @Context CyclicGraphContext cyclicGraphContext);

    @Override
    @Mapping(target = "father", expression = "java(Optional.ofNullable(person.getParents()).orElseGet(Collections::emptyList).stream().filter(FATHER_PREDICATE).map(father -> toPersonEntity(father, cyclicGraphContext)).findAny().orElse(null))")
    @Mapping(target = "mother", expression = "java(Optional.ofNullable(person.getParents()).orElseGet(Collections::emptyList).stream().filter(MOTHER_PREDICATE).map(mother -> toPersonEntity(mother, cyclicGraphContext)).findAny().orElse(null))")
    public abstract PersonEntity toPersonEntity(Person person, @Context CyclicGraphContext cyclicGraphContext);
}
