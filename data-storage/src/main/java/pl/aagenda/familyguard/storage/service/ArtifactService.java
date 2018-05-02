package pl.aagenda.familyguard.storage.service;

import lombok.RequiredArgsConstructor;
import org.neo4j.ogm.exception.core.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.aagenda.familyguard.storage.domain.node.Artifact;
import pl.aagenda.familyguard.storage.repository.ArtifactRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ArtifactService {
    private final ArtifactRepository artifactRepository;

    @Transactional(readOnly = true)
    public Artifact getArtifact(Long id) {
        return artifactRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No artifact with id " + id + " found"));
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
