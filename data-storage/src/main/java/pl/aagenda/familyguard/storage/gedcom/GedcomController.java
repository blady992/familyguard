package pl.aagenda.familyguard.storage.gedcom;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.gedcom4j.model.Gedcom;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;

import static pl.aagenda.familyguard.storage.constants.ResourcePath.Api.API_V1_PATH;
import static pl.aagenda.familyguard.storage.constants.ResourcePath.GEDCOM_PATH;

@RestController
@RequestMapping(path = API_V1_PATH + GEDCOM_PATH)
@RequiredArgsConstructor
public class GedcomController {
    private static final String GEDCOM_MEDIA_TYPE = "application/x-gedcom";

    private final GedcomService gedcomService;

    @PostMapping(consumes = GEDCOM_MEDIA_TYPE)
    @SneakyThrows
    public Gedcom uploadGedcom(@RequestBody byte[] bytes) {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new ByteArrayInputStream(bytes))) {
            return gedcomService.getGedcom(bufferedInputStream);
        }
    }
}
