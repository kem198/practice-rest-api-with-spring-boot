package net.kem198.practice_rest_api_with_spring_boot.constants;

import lombok.Getter;

@Getter
public enum ErrorCodes {
    INVALID_NUMBER_FORMAT("1000"),
    MISSING_PARAMETER("1001"),
    GENERIC_ERROR("1002");

    private final String code;

    ErrorCodes(String code) {
        this.code = code;
    }
}
