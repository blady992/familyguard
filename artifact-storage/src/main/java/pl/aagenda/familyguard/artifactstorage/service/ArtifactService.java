package pl.aagenda.familyguard.artifactstorage.service;

import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.aagenda.familyguard.artifactstorage.domain.Artifact;
import pl.aagenda.familyguard.artifactstorage.domain.ArtifactContent;
import pl.aagenda.familyguard.artifactstorage.mapper.ArtifactMapper;

import java.io.InputStream;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@Transactional
@RequiredArgsConstructor
public class ArtifactService {
    private final GridFsTemplate gridFsTemplate;
    private final ArtifactMapper artifactMapper;

    @Transactional(readOnly = true)
    public Artifact getArtifact(String id) {
        GridFSFile gridFSFile = gridFsTemplate.findOne(query(where("_id").is(id)));
        return artifactMapper.toArtifact(gridFSFile);
    }

    @Transactional(readOnly = true)
    public ArtifactContent getArtifactContent(String id) {
        Artifact artifact = getArtifact(id);
        GridFsResource gridFsResource = gridFsTemplate.getResource(artifact.getFilename());
        return artifactMapper.toArtifactContent(gridFsResource);
    }

    public Artifact saveArtifact(InputStream content, String filename, String contentType) {
        ObjectId id =  gridFsTemplate.store(content, filename, contentType);
        return getArtifact(id.toHexString());
    }
}
