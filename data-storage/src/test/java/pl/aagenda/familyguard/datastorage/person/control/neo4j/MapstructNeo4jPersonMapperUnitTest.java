package pl.aagenda.familyguard.datastorage.person.control.neo4j;

import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.parboiled.common.ImmutableList;
import pl.aagenda.familyguard.datastorage.common.mapstruct.CyclicGraphContext;
import pl.aagenda.familyguard.datastorage.person.entity.PersonEntity;
import pl.aagenda.familyguard.datastorage.person.entity.neo4j.Person;

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
        Person result = objectUnderTest.toPerson(input, new CyclicGraphContext());

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void toPersonEntity() {
        // given
        Person input = preparePerson();
        PersonEntity expectedResult = preparePersonEntity();

        // when
        PersonEntity result = objectUnderTest.toPersonEntity(input, new CyclicGraphContext());

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    private PersonEntity preparePersonEntity() {
        return PersonEntity.builder()
                .id(1L)
                .name("person")
                .sex(MALE)
                .father(PersonEntity.builder()
                        .id(2L)
                        .name("father")
                        .sex(MALE).build())
                .mother(PersonEntity.builder()
                        .id(3L)
                        .name("mother")
                        .sex(FEMALE).build())
                .children(ImmutableList.of(
                        PersonEntity.builder()
                                .id(4L)
                                .name("son")
                                .sex(MALE).build(),
                        PersonEntity.builder()
                                .id(5L)
                                .name("daughter")
                                .sex(FEMALE).build()))
                .build();
    }

    private Person preparePerson() {
        return Person.builder()
                .id(1L)
                .name("person")
                .sex(MALE)
                .parents(ImmutableList.of(
                        Person.builder()
                                .id(2L)
                                .name("father")
                                .sex(MALE).build(),
                        Person.builder()
                                .id(3L)
                                .name("mother")
                                .sex(FEMALE).build()))
                .children(ImmutableList.of(
                        Person.builder()
                                .id(4L)
                                .name("son")
                                .sex(MALE).build(),
                        Person.builder()
                                .id(5L)
                                .name("daughter")
                                .sex(FEMALE).build()))
                .build();
    }
}