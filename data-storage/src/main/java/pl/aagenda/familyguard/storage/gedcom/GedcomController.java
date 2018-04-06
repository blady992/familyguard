package pl.aagenda.familyguard.storage.gedcom;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.gedcom4j.model.Gedcom;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;

@RestController("/gedcom")
@RequiredArgsConstructor
public class GedcomController {

    private final GedcomService gedcomService;

    @PostMapping(consumes = "application/x-gedcom")
    @SneakyThrows
    public Gedcom uploadGedcom(@RequestBody byte[] bytes) {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new ByteArrayInputStream(bytes))) {
            return gedcomService.getGedcom(bufferedInputStream);
        }
    }
}
