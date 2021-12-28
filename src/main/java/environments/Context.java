package environments;

public enum Context {
    NATIVE("NATIVE_APP"),
    WEBVIEW("WEBVIEW_com.wdiodemoapp");

    private final String context;

    Context(String context) {
        this.context = context;
    }

    public String getContext() {
        return this.context;
    }
}
