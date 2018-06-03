package pl.aagenda.familyguard.artifactstorage.mapper;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.stereotype.Component;
import pl.aagenda.familyguard.artifactstorage.domain.Artifact;

@Component
public class ArtifactMapper {
    public Artifact toArtifact(GridFSFile gridFSFile) {
        return Artifact.builder()
                .id(gridFSFile.getId().asString().getValue())
                .contentType(gridFSFile.getMetadata().get("_contentType", String.class))
                .contentLength(gridFSFile.getLength())
                .contentMd5(gridFSFile.getMD5())
                .uploadedDate(gridFSFile.getUploadDate())
                .build();
    }
}
