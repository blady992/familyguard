package pl.aagenda.familyguard.artifactstorage.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.aagenda.familyguard.artifactstorage.domain.Artifact;
import pl.aagenda.familyguard.artifactstorage.domain.ArtifactContent;
import pl.aagenda.familyguard.artifactstorage.service.ArtifactService;

import java.util.UUID;

import static org.springframework.http.MediaType.parseMediaType;
import static pl.aagenda.familyguard.artifactstorage.constant.ResourcePath.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1_PREFIX_PATH + ARTIFACTS_PATH)
public class ArtifactController {
    private final ArtifactService artifactService;

    @GetMapping(path = ID_PATH_VARIABLE)
    public Artifact getArtifact(@PathVariable String id) {
        return artifactService.getArtifact(id);
    }

    @GetMapping(path = ID_PATH_VARIABLE + CONTENT_PATH)
    public ResponseEntity<InputStreamResource> getArtifactContent(@PathVariable String id) {
        ArtifactContent artifactContent = artifactService.getArtifactContent(id);
        return ResponseEntity.ok()
                .contentType(parseMediaType(artifactContent.getContentType()))
                .contentLength(artifactContent.getContentLength())
                .body(new InputStreamResource(artifactContent.getInputStream()));
    }

    @PostMapping
    @SneakyThrows
    public Artifact addArtifact(@RequestParam(FILE_REQUEST_PARAM_NAME) MultipartFile multipartFile) {
        return artifactService.saveArtifact(
                multipartFile.getInputStream(),
                UUID.randomUUID().toString(),
                multipartFile.getContentType());
    }
}
