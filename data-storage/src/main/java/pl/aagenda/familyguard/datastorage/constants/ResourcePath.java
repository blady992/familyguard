package pl.aagenda.familyguard.datastorage.constants;

public final class ResourcePath {
    public final class Api {
        public static final String API_PATH = "/api";
        public static final String V1_PATH = "/v1";

        public static final String API_V1_PATH = API_PATH + V1_PATH;
    }

    public static final String ARTIFACTS_PATH = "/artifacts";
    public static final String EVENTS_PATH = "/events";
    public static final String PEOPLE_PATH = "/people";
    public static final String RESOURCES_PATH = "/resources";

    public static final String GEDCOM_PATH = "/gedcom";

    public static final String ID_PATH_VARIABLE = "/{id}";

    private ResourcePath() {
        // INTENTIONALLY LEFT BLANK
    }
}
