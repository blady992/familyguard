package pl.aagenda.familyguard.storage.service;

import lombok.RequiredArgsConstructor;
import org.neo4j.ogm.exception.core.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.aagenda.familyguard.storage.domain.node.Resource;
import pl.aagenda.familyguard.storage.repository.ResourceRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ResourceService {
    private final ResourceRepository resourceRepository;

    @Transactional(readOnly = true)
    public Resource getResource(Long id) {
        return resourceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No resource with id " + id + " found"));
    }

    @Transactional(readOnly = true)
    public Page<Resource> getAllResources(Pageable pageable) {
        return resourceRepository.findAll(pageable);
    }

    public Resource saveResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    public void deleteResource(Long id) {
        resourceRepository.deleteById(id);
    }
}
