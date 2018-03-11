package pl.aagenda.familyguard.storage.constants;

public final class RelationshipConstants {

    public static final String RELATES = "RELATES";
    public static final String WIELDS = "WIELDS";
    public static final String PARTICIPATES = "PARTICIPATES";
    public static final String FEATURES = "FEATURES";

    public static final String CONTAINS_ARTIFACT = "CONTAINS_ARTIFACT";
    public static final String CONTAINS_EVENT = "CONTAINS_EVENT";
    public static final String CONTAINS_PERSON = "CONTAINS_PERSON";

    private RelationshipConstants() {
        throw new IllegalAccessError();
    }
}
