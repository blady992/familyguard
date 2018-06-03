package pl.aagenda.familyguard.artifactstorage.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.aagenda.familyguard.artifactstorage.service.ArtifactService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/artifacts")
public class ArtifactController {
    private final ArtifactService artifactService;

    @GetMapping(path = "/{id}")
    public Object getArtifact(@PathVariable String id) {
        return artifactService.getArtifact(id);
    }

    @PostMapping
    @SneakyThrows
    public Object addArtifact(@RequestParam("file") MultipartFile multipartFile) {
        return artifactService.saveArtifact(multipartFile.getInputStream(), multipartFile.getOriginalFilename(), multipartFile.getContentType());
    }
}
