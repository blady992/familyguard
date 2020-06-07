package pl.aagenda.familyguard.datastorage.artifact;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArtifactService {
    private final ArtifactRepository artifactRepository;

    @Transactional(readOnly = true)
    public Artifact getArtifact(Long id) {
        return artifactRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No artifact with id " + id + " found"));
    }

    @Transactional(readOnly = true)
    public Page<Artifact> getAllArtifacts(Pageable pageable) {
        return artifactRepository.findAll(pageable);
    }

    public Artifact saveArtifact(Artifact artifact) {
        return artifactRepository.save(artifact);
    }

    public void deleteArtifact(Long id) {
        artifactRepository.deleteById(id);
    }
}
