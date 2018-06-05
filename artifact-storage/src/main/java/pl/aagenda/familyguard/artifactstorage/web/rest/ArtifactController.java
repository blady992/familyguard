package pl.aagenda.familyguard.artifactstorage.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.aagenda.familyguard.artifactstorage.domain.ArtifactContent;
import pl.aagenda.familyguard.artifactstorage.service.ArtifactService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/artifacts")
public class ArtifactController {
    private final ArtifactService artifactService;

    @GetMapping(path = "/{id}")
    public Object getArtifact(@PathVariable String id) {
        return artifactService.getArtifact(id);
    }

    @GetMapping(path = "/{id}/content")
    public ResponseEntity<InputStreamResource> getArtifactContent(@PathVariable String id) {
        ArtifactContent artifactContent = artifactService.getArtifactContent(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(artifactContent.getContentType()))
                .contentLength(artifactContent.getContentLength())
                .body(new InputStreamResource(artifactContent.getInputStream()));
    }

    @PostMapping
    @SneakyThrows
    public Object addArtifact(@RequestParam("file") MultipartFile multipartFile) {
        return artifactService.saveArtifact(multipartFile.getInputStream(), UUID.randomUUID().toString(), multipartFile.getContentType());
    }
}
