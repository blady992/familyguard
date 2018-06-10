package pl.aagenda.familyguard.artifactstorage.constant;

public final class ResourcePath {
    public static final String API_V1_PREFIX_PATH = "/api/v1";
    public static final String ARTIFACTS_PATH = "/artifacts";
    public static final String ID_PATH_VARIABLE = "/{id}";
    public static final String CONTENT_PATH = "/content";

    public static final String FILE_REQUEST_PARAM_NAME = "file";

    private ResourcePath() {
        throw new IllegalAccessError();
    }
}
