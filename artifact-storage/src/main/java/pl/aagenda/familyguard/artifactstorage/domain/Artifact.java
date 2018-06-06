package pl.aagenda.familyguard.artifactstorage.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artifact {
    private String id;
    private String filename;
    private Date uploadDate;
    private String contentType;
    private String contentMd5;
    private long contentLength;
    private Map<String, Object> metadata;
}
