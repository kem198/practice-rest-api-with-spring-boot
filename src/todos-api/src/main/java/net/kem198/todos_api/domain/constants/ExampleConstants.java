package net.kem198.todos_api.domain.constants;

public enum ExampleConstants {
    GENERIC_ERROR("Generic Error");

    private final String title;

    ExampleConstants(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
