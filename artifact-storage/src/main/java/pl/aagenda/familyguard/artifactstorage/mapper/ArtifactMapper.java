package pl.aagenda.familyguard.artifactstorage.mapper;

import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.SneakyThrows;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Component;
import pl.aagenda.familyguard.artifactstorage.domain.Artifact;
import pl.aagenda.familyguard.artifactstorage.domain.ArtifactContent;

@Component
public class ArtifactMapper {
    public Artifact toArtifact(GridFSFile gridFSFile) {
        return Artifact.builder()
                .id(gridFSFile.getObjectId().toHexString())
                .filename(gridFSFile.getFilename())
                .contentType(gridFSFile.getMetadata().get("_contentType", String.class))
                .contentLength(gridFSFile.getLength())
                .contentMd5(gridFSFile.getMD5())
                .uploadedDate(gridFSFile.getUploadDate())
                .metadata(gridFSFile.getMetadata())
                .build();
    }

    @SneakyThrows
    public ArtifactContent toArtifactContent(GridFsResource gridFsResource) {
        return ArtifactContent.builder()
                .filename(gridFsResource.getFilename())
                .contentType(gridFsResource.getContentType())
                .inputStream(gridFsResource.getInputStream())
                .contentLength(gridFsResource.contentLength())
                .build();
    }
}
