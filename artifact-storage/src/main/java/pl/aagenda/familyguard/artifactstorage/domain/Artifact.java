package pl.aagenda.familyguard.artifactstorage.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artifact {
    private String id;
    private String filename;
    private ZonedDateTime uploadDate;
    private String contentType;
    private long contentLength;
    private Map<String, Object> metadata;
}
