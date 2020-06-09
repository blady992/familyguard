package pl.aagenda.familyguard.datastorage.constants;

public final class RelationshipConstants {
    public final class Person {
        public static final String PARENT_OF = "PARENT_OF";
        public static final String PARTICIPATES = "PARTICIPATES";
    }

    private RelationshipConstants() {
        throw new IllegalAccessError();
    }
}
