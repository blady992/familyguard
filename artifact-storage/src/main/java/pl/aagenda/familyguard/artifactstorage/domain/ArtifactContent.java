package pl.aagenda.familyguard.artifactstorage.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtifactContent {
    private InputStream inputStream;
    private Long contentLength;
    private String filename;
    private String contentType;
}
