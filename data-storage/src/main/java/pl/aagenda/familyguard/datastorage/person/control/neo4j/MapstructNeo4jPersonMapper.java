package pl.aagenda.familyguard.datastorage.person.control.neo4j;

import com.google.common.collect.ImmutableMap;
import io.vavr.collection.Stream;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pl.aagenda.familyguard.datastorage.person.entity.PersonEntity;
import pl.aagenda.familyguard.datastorage.person.entity.neo4j.Person;
import pl.aagenda.familyguard.datastorage.person.entity.neo4j.RelativePerson;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ERROR)
public abstract class MapstructNeo4jPersonMapper implements Neo4jPersonMapper {

    @Override
    @Mapping(target = "relatives", ignore = true)
    public abstract Person toPerson(PersonEntity personEntity);

    @Override
    @Mapping(target = "relatives", ignore = true)
    public abstract PersonEntity toPersonEntity(Person person);

    @AfterMapping
    protected void extractRelatedPeople(@MappingTarget PersonEntity mappedPersonEntity, Person person) {
        ofNullable(person.getRelatives())
                .ifPresent(relatives ->
                        mappedPersonEntity.setRelatives(ImmutableMap.copyOf(
                                Stream.ofAll(relatives)
                                        .groupBy(RelativePerson::getName)
                                        .mapValues(relativePeople ->
                                                relativePeople.map(RelativePerson::getRelative)
                                                        .map(this::toPersonEntity)
                                                        .collect(toList()))
                                        .toJavaMap())));
    }

    @AfterMapping
    protected void extractRelatedPeople(@MappingTarget Person mappedPerson, PersonEntity personEntity) {
        ofNullable(personEntity.getRelatives())
                .ifPresent(relatives ->
                        mappedPerson.setRelatives(relatives
                                .entrySet()
                                .stream()
                                .flatMap(relativePersonEntry ->
                                        relativePersonEntry.getValue()
                                                .stream()
                                                .map(relatedPersonEntity -> {
                                                    RelativePerson relativePerson = new RelativePerson();
                                                    relativePerson.setName(relativePersonEntry.getKey());
                                                    relativePerson.setRoot(mappedPerson);
                                                    relativePerson.setRelative(toPerson(relatedPersonEntity));
                                                    return relativePerson;
                                                }))
                                .collect(toList())));
    }
}
