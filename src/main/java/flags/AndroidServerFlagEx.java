package flags;

import io.appium.java_client.service.local.flags.ServerArgument;

public enum AndroidServerFlagEx implements ServerArgument {
    ALLOW_INSECURE("--allow-insecure"),
    CHROMEDRIVER_EXECUTABLE("--chromedriver-executable");
    private final String arg;

    AndroidServerFlagEx(String arg) {
        this.arg = arg;
    }

    public String getArgument() {
        return this.arg;
    }
}
