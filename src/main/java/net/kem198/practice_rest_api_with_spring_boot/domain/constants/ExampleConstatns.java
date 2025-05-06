package net.kem198.practice_rest_api_with_spring_boot.domain.constants;

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
