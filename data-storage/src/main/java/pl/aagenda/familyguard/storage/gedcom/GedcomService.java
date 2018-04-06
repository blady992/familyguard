package pl.aagenda.familyguard.storage.gedcom;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Gedcom;
import org.gedcom4j.parser.GedcomParser;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.IOException;

@Service
public class GedcomService {
    public Gedcom getGedcom(BufferedInputStream inputStream) throws IOException, GedcomParserException {
        GedcomParser gedcomParser = new GedcomParser();
        gedcomParser.load(inputStream);
        return gedcomParser.getGedcom();
    }
}
