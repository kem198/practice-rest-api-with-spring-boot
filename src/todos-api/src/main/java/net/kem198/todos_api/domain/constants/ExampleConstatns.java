package net.kem198.todos_api.domain.constants;

public enum ExampleConstatns {
    GENERIC_ERROR("Generic Error");

    private final String title;

    ExampleConstatns(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
