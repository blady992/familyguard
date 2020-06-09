package pl.aagenda.familyguard.artifactstorage.mapper;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.MediaType;
import pl.aagenda.familyguard.artifactstorage.domain.Artifact;
import pl.aagenda.familyguard.artifactstorage.domain.ArtifactContent;

import java.io.IOException;
import java.util.Optional;

@Mapper(componentModel = "spring", imports = { Optional.class, MediaType.class })
public interface ArtifactMapper {

    @Mapping(target = "id", expression = "java(gridFSFile.getObjectId().toHexString())")
    @Mapping(target = "contentType", expression = "java(Optional.ofNullable(gridFSFile.getMetadata()).map(metadata -> metadata.get(\"_contentType\", String.class)).orElse(MediaType.APPLICATION_OCTET_STREAM_VALUE))")
    @Mapping(target = "contentLength", source = "length")
    Artifact toArtifact(GridFSFile gridFSFile);

    @Mapping(target = "contentLength", expression = "java(gridFsResource.contentLength())")
    ArtifactContent toArtifactContent(GridFsResource gridFsResource) throws IOException;
}
