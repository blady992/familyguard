package pl.aagenda.familyguard.artifactstorage.service;

import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.aagenda.familyguard.artifactstorage.domain.Artifact;
import pl.aagenda.familyguard.artifactstorage.mapper.ArtifactMapper;

import java.io.InputStream;

@Service
@Transactional
@RequiredArgsConstructor
public class ArtifactService {
    private final GridFsTemplate gridFsTemplate;
    private final ArtifactMapper artifactMapper;

    @Transactional(readOnly = true)
    public Artifact getArtifact(String id) {
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(id)));
        return artifactMapper.toArtifact(gridFSFile);
    }

    public Artifact saveArtifact(InputStream content, String filename, String contentType) {
        ObjectId id =  gridFsTemplate.store(content, filename, contentType);
        return getArtifact(id.toHexString());
    }
}
