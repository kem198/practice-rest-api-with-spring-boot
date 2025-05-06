package net.kem198.practice_rest_api_with_spring_boot.api.common.error;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import net.kem198.practice_rest_api_with_spring_boot.domain.exception.common.BusinessException;

@ControllerAdvice
public class RestGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private Map<String, Object> toErrorDetail(FieldError fieldError) {
        return Map.of(
                "field", fieldError.getField(),
                "rejectedValue", fieldError.getRejectedValue(),
                "code", fieldError.getCode(),
                "objectName", fieldError.getObjectName(),
                "message", fieldError.getDefaultMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ProblemDetail> handleBusinessException(BusinessException ex) {
        return ResponseEntity
                .status(ex.getProblemDetail().getStatus())
                .body(ex.getProblemDetail());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleSystemError(Exception ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setDetail("An unexpected error occurred. Please contact support if the problem persists.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problemDetail);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<Map<String, Object>> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(this::toErrorDetail)
                .collect(Collectors.toList());

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail("Validation failed for the request.");
        problemDetail.setProperty("errors", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

}
