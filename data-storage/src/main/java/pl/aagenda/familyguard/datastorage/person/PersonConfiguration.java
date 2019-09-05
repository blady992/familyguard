package pl.aagenda.familyguard.datastorage.person;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.aagenda.familyguard.datastorage.person.control.PersonControl;
import pl.aagenda.familyguard.datastorage.person.control.TransactionalPersonControlDecorator;
import pl.aagenda.familyguard.datastorage.person.control.neo4j.Neo4jPersonMapper;
import pl.aagenda.familyguard.datastorage.person.control.neo4j.Neo4jRepositoryPersonControl;
import pl.aagenda.familyguard.datastorage.person.control.neo4j.PersonNeo4jRepository;

@Configuration
public class PersonConfiguration {

    @Bean
    PersonControl personControl(PersonNeo4jRepository repository, Neo4jPersonMapper mapper) {
        return new TransactionalPersonControlDecorator(new Neo4jRepositoryPersonControl(repository, mapper));
    }
}
