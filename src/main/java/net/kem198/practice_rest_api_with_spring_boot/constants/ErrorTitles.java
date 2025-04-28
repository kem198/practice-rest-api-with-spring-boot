package net.kem198.practice_rest_api_with_spring_boot.constants;

public enum ErrorTitles {
    INVALID_NUMBER_FORMAT("Invalid Number Format"),
    MISSING_PARAMETER("Missing Parameter"),
    GENERIC_ERROR("Generic Error");

    private final String title;

    ErrorTitles(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
