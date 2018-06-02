package pl.aagenda.familyguard.datastorage.utils;

import org.neo4j.harness.junit.Neo4jRule;

public final class Neo4jTestUtils {
    public static Neo4jRule prepareNeo4jServer() {
        return new Neo4jRule()
                .withConfig("dbms.security.auth_enabled", "false")
                .withConfig("dbms.connector.bolt.address", "127.0.0.1:7000");
    }

    private Neo4jTestUtils() {
        throw new IllegalAccessError();
    }
}
