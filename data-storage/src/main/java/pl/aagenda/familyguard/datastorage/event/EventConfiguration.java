package pl.aagenda.familyguard.datastorage.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.aagenda.familyguard.datastorage.event.control.EventControl;
import pl.aagenda.familyguard.datastorage.event.control.TransactionalEventControlDecorator;
import pl.aagenda.familyguard.datastorage.event.control.neo4j.EventNeo4jRepository;
import pl.aagenda.familyguard.datastorage.event.control.neo4j.Neo4jEventMapper;
import pl.aagenda.familyguard.datastorage.event.control.neo4j.Neo4jRepositoryEventControl;

@Configuration
public class EventConfiguration {

    @Bean
    EventControl eventControl(EventNeo4jRepository repository, Neo4jEventMapper mapper) {
        return new TransactionalEventControlDecorator(new Neo4jRepositoryEventControl(repository, mapper));
    }
}
