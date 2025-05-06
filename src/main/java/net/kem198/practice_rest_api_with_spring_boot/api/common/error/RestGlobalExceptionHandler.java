package net.kem198.practice_rest_api_with_spring_boot.api.common.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import net.kem198.practice_rest_api_with_spring_boot.domain.exception.common.BusinessException;

@ControllerAdvice
public class RestGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        return ResponseEntity
                .status(ex.getProblemDetail().getStatus())
                .body(ex.getProblemDetail());
    }
}
