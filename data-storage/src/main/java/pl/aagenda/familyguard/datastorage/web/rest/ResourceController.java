package pl.aagenda.familyguard.datastorage.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import pl.aagenda.familyguard.datastorage.domain.node.Resource;
import pl.aagenda.familyguard.datastorage.service.ResourceService;

import static pl.aagenda.familyguard.datastorage.constants.ResourcePath.Api.API_V1_PATH;
import static pl.aagenda.familyguard.datastorage.constants.ResourcePath.ID_PATH_VARIABLE;
import static pl.aagenda.familyguard.datastorage.constants.ResourcePath.RESOURCES_PATH;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = API_V1_PATH + RESOURCES_PATH)
public class ResourceController {
    private final ResourceService resourceService;

    @GetMapping(ID_PATH_VARIABLE)
    public Resource getResource(@PathVariable Long id) {
        return resourceService.getResource(id);
    }

    @GetMapping
    public Page<Resource> getResources(@PageableDefault Pageable pageable) {
        return resourceService.getAllResources(pageable);
    }

    @PostMapping
    public Resource saveResource(@RequestBody Resource resource) {
        return resourceService.saveResource(resource);
    }

    @DeleteMapping(ID_PATH_VARIABLE)
    public void deleteResource(@PathVariable Long id) {
        resourceService.deleteResource(id);
    }
}
