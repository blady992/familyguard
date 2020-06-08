package pl.aagenda.familyguard.datastorage.utils;

import org.neo4j.configuration.helpers.SocketAddress;
import org.neo4j.harness.junit.rule.Neo4jRule;

import static org.neo4j.configuration.GraphDatabaseSettings.auth_enabled;
import static org.neo4j.configuration.connectors.BoltConnector.listen_address;

public final class Neo4jTestUtils {
    public static Neo4jRule prepareNeo4jServer() {
        return new Neo4jRule()
                .withConfig(auth_enabled, false)
                .withConfig(listen_address, new SocketAddress(7123));
    }

    private Neo4jTestUtils() {
        throw new IllegalAccessError();
    }
}
