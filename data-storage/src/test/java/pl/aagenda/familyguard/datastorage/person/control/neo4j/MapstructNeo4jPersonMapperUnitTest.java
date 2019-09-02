package pl.aagenda.familyguard.datastorage.person.control.neo4j;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.parboiled.common.ImmutableList;
import pl.aagenda.familyguard.datastorage.person.entity.PersonEntity;
import pl.aagenda.familyguard.datastorage.person.entity.neo4j.Person;
import pl.aagenda.familyguard.datastorage.person.entity.neo4j.RelativePerson;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.aagenda.familyguard.datastorage.person.entity.Sex.FEMALE;
import static pl.aagenda.familyguard.datastorage.person.entity.Sex.MALE;

public class MapstructNeo4jPersonMapperUnitTest {

    private MapstructNeo4jPersonMapper objectUnderTest = Mappers.getMapper(MapstructNeo4jPersonMapper.class);

    @Test
    public void toPerson() {
        // given
        PersonEntity input = preparePersonEntity();
        Person expectedResult = preparePerson();

        // when
        Person result = objectUnderTest.toPerson(input);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void toPersonEntity() {
        // given
        Person input = preparePerson();
        PersonEntity expectedResult = preparePersonEntity();

        // when
        PersonEntity result = objectUnderTest.toPersonEntity(input);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    private PersonEntity preparePersonEntity() {
        return PersonEntity.builder()
                .id(1L)
                .name("person")
                .sex(MALE)
                .relatives(ImmutableMap.of(
                        "PARENT", ImmutableList.of(
                                PersonEntity.builder()
                                        .id(2L)
                                        .name("father")
                                        .sex(MALE).build(),
                                PersonEntity.builder()
                                        .id(3L)
                                        .name("mother")
                                        .sex(FEMALE).build()
                        ),
                        "SPOUSE", ImmutableList.of(
                                PersonEntity.builder()
                                        .id(4L)
                                        .name("wife")
                                        .sex(FEMALE).build()
                        )
                )).build();
    }

    private Person preparePerson() {
        Person person = Person.builder()
                .id(1L)
                .name("person")
                .sex(MALE)
                .build();
        person.setRelatives(ImmutableList.of(
                RelativePerson.builder()
                        .name("PARENT")
                        .root(person).relative(Person.builder()
                        .id(2L)
                        .name("father")
                        .sex(MALE).build())
                        .build(),
                RelativePerson.builder()
                        .name("PARENT")
                        .root(person).relative(Person.builder()
                        .id(3L)
                        .name("mother")
                        .sex(FEMALE).build())
                        .build(),
                RelativePerson.builder()
                        .name("SPOUSE")
                        .root(person).relative(Person.builder()
                        .id(4L)
                        .name("wife")
                        .sex(FEMALE).build())
                        .build()
        ));
        return person;
    }
}