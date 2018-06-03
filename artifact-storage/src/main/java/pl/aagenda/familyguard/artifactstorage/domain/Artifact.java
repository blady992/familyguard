package pl.aagenda.familyguard.artifactstorage.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class Artifact implements Serializable {

    private String id;
    private Date uploadedDate;
    private String contentType;
    private String contentMd5;
    private long contentLength;
}
