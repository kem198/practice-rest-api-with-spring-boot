package net.kem198.practice_rest_api_with_spring_boot.dto;

import lombok.Getter;
import lombok.Setter;
import net.kem198.practice_rest_api_with_spring_boot.constants.ErrorCodes;

@Getter
@Setter
public class ErrorResponseDto {
    private String code;
    private String message;

    public ErrorResponseDto(ErrorCodes code, String message) {
        this.code = code.getCode();
        this.message = message;
    }
}
