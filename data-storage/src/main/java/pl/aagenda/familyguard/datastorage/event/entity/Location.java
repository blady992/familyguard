package pl.aagenda.familyguard.datastorage.event.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    private String name;
    private Double longitude;
    private Double latitude;
}
