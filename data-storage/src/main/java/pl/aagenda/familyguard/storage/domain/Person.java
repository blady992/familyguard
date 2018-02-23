package pl.aagenda.familyguard.storage.domain;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

import java.io.Serializable;

@Data
public class Person implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
