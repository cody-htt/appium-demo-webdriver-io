package environments;

public enum PlatformType {
    ios("ios"),
    android("android");

    private final String name;

    PlatformType(String name) { this.name = name; }

    public String getName() { return name; }
}
