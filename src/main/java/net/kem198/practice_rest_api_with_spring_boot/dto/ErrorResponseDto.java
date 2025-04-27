package net.kem198.practice_rest_api_with_spring_boot.dto;

import net.kem198.practice_rest_api_with_spring_boot.constants.ErrorCodes;

public class ErrorResponseDto {
    private String code;
    private String message;

    /**
     * Constructor for ErrorResponse.
     *
     * @param code    The error code.
     * @param message The error message.
     */
    public ErrorResponseDto(ErrorCodes code, String message) {
        this.code = code.getCode();
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
