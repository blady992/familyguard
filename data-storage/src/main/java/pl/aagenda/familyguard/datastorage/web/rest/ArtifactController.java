package pl.aagenda.familyguard.datastorage.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import pl.aagenda.familyguard.datastorage.domain.node.Artifact;
import pl.aagenda.familyguard.datastorage.service.ArtifactService;

import static pl.aagenda.familyguard.datastorage.constants.ResourcePath.ARTIFACTS_PATH;
import static pl.aagenda.familyguard.datastorage.constants.ResourcePath.Api.API_V1_PATH;
import static pl.aagenda.familyguard.datastorage.constants.ResourcePath.ID_PATH_VARIABLE;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = API_V1_PATH + ARTIFACTS_PATH)
public class ArtifactController {
    private final ArtifactService artifactService;

    @GetMapping(ID_PATH_VARIABLE)
    public Artifact getArtifact(@PathVariable Long id) {
        return artifactService.getArtifact(id);
    }

    @GetMapping
    public Page<Artifact> getArtifacts(@PageableDefault Pageable pageable) {
        return artifactService.getAllArtifacts(pageable);
    }

    @PostMapping
    public Artifact saveArtifact(@RequestBody Artifact artifact) {
        return artifactService.saveArtifact(artifact);
    }

    @DeleteMapping(ID_PATH_VARIABLE)
    public void deleteArtifact(@PathVariable Long id) {
        artifactService.deleteArtifact(id);
    }
}
