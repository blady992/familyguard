package pl.aagenda.familyguard.datastorage.domain;

import java.util.Optional;

import static java.util.Arrays.stream;

public enum Sex {
    MALE, FEMALE, UNKNOWN;

    public static Optional<Sex> fromString(String str) {
        return stream(Sex.values())
                .filter(sex -> sex.name().equalsIgnoreCase(str))
                .findAny();
    }
}
