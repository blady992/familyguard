package pl.aagenda.familyguard.datastorage.event.control.neo4j;

import org.junit.Test;
import org.mapstruct.factory.Mappers;
import pl.aagenda.familyguard.datastorage.common.mapstruct.CyclicGraphContext;
import pl.aagenda.familyguard.datastorage.event.entity.EventEntity;
import pl.aagenda.familyguard.datastorage.event.entity.Location;
import pl.aagenda.familyguard.datastorage.event.entity.neo4j.Event;

import static org.assertj.core.api.Assertions.assertThat;

public class MapstructNeo4jEventMapperUnitTest {

    private MapstructNeo4jEventMapper objectUnderTest = Mappers.getMapper(MapstructNeo4jEventMapper.class);

    @Test
    public void toPerson() {
        // given
        EventEntity input = prepareEventEntity();
        Event expectedResult = prepareEvent();

        // when
        Event result = objectUnderTest.toEvent(input, new CyclicGraphContext());

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void toEventEntity() {
        // given
        Event input = prepareEvent();
        EventEntity expectedResult = prepareEventEntity();

        // when
        EventEntity result = objectUnderTest.toEventEntity(input, new CyclicGraphContext());

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    private EventEntity prepareEventEntity() {
        return EventEntity.builder()
                .id(1L)
                .name("event name")
                .description("event description")
                .location(Location.builder()
                        .name("FTIMS")
                        .latitude(51.747237)
                        .longitude(19.4534342)
                        .build())
                .build();
    }

    private Event prepareEvent() {
        return Event.builder()
                .id(1L)
                .name("event name")
                .description("event description")
                .locationName("FTIMS")
                .locationLatitude(51.747237)
                .locationLongitude(19.4534342)
                .build();
    }
}