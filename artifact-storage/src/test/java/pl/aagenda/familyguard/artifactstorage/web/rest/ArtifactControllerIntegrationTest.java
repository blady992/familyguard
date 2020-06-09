package pl.aagenda.familyguard.artifactstorage.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.aagenda.familyguard.artifactstorage.domain.Artifact;

import java.time.ZonedDateTime;

import static java.lang.Math.abs;
import static java.time.Duration.between;
import static java.time.ZonedDateTime.now;
import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.gridfs.GridFsCriteria.whereFilename;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.aagenda.familyguard.artifactstorage.constant.ResourcePath.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ArtifactControllerIntegrationTest {
    private static final String ARTIFACTS_BASE_PATH = API_V1_PREFIX_PATH + ARTIFACTS_PATH;
    private static final long MAX_OFFSET_SECONDS = 5L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GridFsOperations gridFsOperations;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("classpath:file-uploads/small_pic.png")
    private Resource smallPic;

    @Test
    @DirtiesContext
    public void shouldAddArtifact() throws Exception {
        // given
        MockMultipartFile mockMultipartFile = new MockMultipartFile(FILE_REQUEST_PARAM_NAME, smallPic.getInputStream());

        // when
        String json = mockMvc.perform(
                multipart(ARTIFACTS_BASE_PATH)
                        .file(mockMultipartFile))
                .andExpect(
                        status().isOk())
                .andReturn().getResponse().getContentAsString();

        // then
        Artifact result = objectMapper.readValue(json, Artifact.class);
        GridFSFile saved = gridFsOperations.findOne(query(whereFilename().is(result.getFilename())));
        assertThat(saved).isNotNull();
        assertThat(result.getId()).isEqualTo(saved.getId().asObjectId().getValue().toHexString());
        assertThat(result.getContentLength()).isEqualTo(smallPic.contentLength());
        assertThat(result.getUploadDate()).isBetween(now().minusSeconds(MAX_OFFSET_SECONDS), now());
    }

    @Test
    @DirtiesContext
    public void shouldGetArtifact() throws Exception {
        // given
        String filename = "filename";
        ObjectId storedObjectId = gridFsOperations.store(smallPic.getInputStream(), filename, IMAGE_PNG_VALUE);

        // when
        mockMvc.perform(
                get(ARTIFACTS_BASE_PATH + "/" + storedObjectId.toHexString()))
                .andExpect(
                        status().isOk())
                .andExpect(jsonPath("$.id", is(storedObjectId.toHexString())))
                .andExpect(jsonPath("$.filename", is(filename)))
                .andExpect(jsonPath("$.uploadDate", isCloseToNow()))
                .andExpect(jsonPath("$.contentType", is(IMAGE_PNG_VALUE)))
                .andExpect(jsonPath("$.contentLength", is((int)smallPic.contentLength())))
                .andExpect(jsonPath("$.metadata", hasEntry("_contentType", IMAGE_PNG_VALUE)));
    }

    @Test
    @DirtiesContext
    public void shouldGetArtifactContent() throws Exception {
        // given
        String filename = "filename";
        byte[] expectedContent = IOUtils.toByteArray(smallPic.getInputStream());
        ObjectId storedObjectId = gridFsOperations.store(smallPic.getInputStream(), filename, IMAGE_PNG_VALUE);

        // when
        byte[] response = mockMvc.perform(
                get(ARTIFACTS_BASE_PATH + "/" + storedObjectId.toHexString() + CONTENT_PATH))
                .andExpect(
                        status().isOk())
                .andReturn().getResponse().getContentAsByteArray();
        // then

        assertThat(response).isEqualTo(expectedContent);
    }

    private Matcher<String> isCloseToNow() {
        return new BaseMatcher<>() {
            private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BaseMatcher.class);

            @Override
            public boolean matches(Object o) {
                ZonedDateTime dateTime = ZonedDateTime.parse(o.toString(), ISO_DATE_TIME);
                long duration = abs(between(dateTime, now()).getSeconds());
                return duration <= MAX_OFFSET_SECONDS;
            }

            @Override
            public void describeTo(Description description) {
                log.error(description.toString());
            }
        };
    }
}
