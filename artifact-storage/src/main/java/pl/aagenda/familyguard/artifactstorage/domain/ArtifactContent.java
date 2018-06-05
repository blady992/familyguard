package pl.aagenda.familyguard.artifactstorage.domain;

import lombok.Builder;
import lombok.Data;

import java.io.InputStream;

@Data
@Builder
public class ArtifactContent {
    private InputStream inputStream;
    private Long contentLength;
    private String filename;
    private String contentType;
}
