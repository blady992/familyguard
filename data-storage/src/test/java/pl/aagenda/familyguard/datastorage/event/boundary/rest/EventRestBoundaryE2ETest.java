package pl.aagenda.familyguard.datastorage.event.boundary.rest;

import org.assertj.core.util.Files;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.harness.junit.Neo4jRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.aagenda.familyguard.datastorage.constants.ResourcePath.Api.API_V1_PATH;
import static pl.aagenda.familyguard.datastorage.constants.ResourcePath.EVENTS_PATH;
import static pl.aagenda.familyguard.datastorage.utils.Neo4jTestUtils.prepareNeo4jServer;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class EventRestBoundaryE2ETest {
    @Rule
    public Neo4jRule neo4jRule = prepareNeo4jServer()
            .withFixture(new File("src/test/resources/cypher/events.cyp"));

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGetEvents() throws Exception {
        mockMvc.perform(
                get(API_V1_PATH + EVENTS_PATH))
                .andExpect(
                        status().isOk())
                .andExpect(
                        content().json(Files.contentOf(new File("src/test/resources/json/events.json"), UTF_8)));
    }

    @Test
    public void shouldGetEvent() throws Exception {
        mockMvc.perform(
                get(API_V1_PATH + EVENTS_PATH + "/0"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        content().json(Files.contentOf(new File("src/test/resources/json/event.json"), UTF_8)));
    }
}