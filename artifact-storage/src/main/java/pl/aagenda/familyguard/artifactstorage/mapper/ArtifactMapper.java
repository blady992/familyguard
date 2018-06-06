package pl.aagenda.familyguard.artifactstorage.mapper;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import pl.aagenda.familyguard.artifactstorage.domain.Artifact;
import pl.aagenda.familyguard.artifactstorage.domain.ArtifactContent;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface ArtifactMapper {

    @Mapping(target = "id", expression = "java(gridFSFile.getObjectId().toHexString())")
    @Mapping(target = "contentType", expression = "java(gridFSFile.getMetadata().get(\"_contentType\", String.class))")
    @Mapping(target = "contentLength", source = "length")
    @Mapping(target = "contentMd5", source = "MD5")
    Artifact toArtifact(GridFSFile gridFSFile);

    @Mapping(target = "contentLength", expression = "java(gridFsResource.contentLength())")
    ArtifactContent toArtifactContent(GridFsResource gridFsResource) throws IOException;
}
