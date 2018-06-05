package pl.aagenda.familyguard.artifactstorage.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
@Builder
public class Artifact {
    private String id;
    private String filename;
    private Date uploadedDate;
    private String contentType;
    private String contentMd5;
    private long contentLength;
    private Map<String, Object> metadata;
}
