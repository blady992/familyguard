package pl.aagenda.familyguard.datastorage.event.boundary.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventMinimalRestDTO {
    private Long id;
    private String name;
}
