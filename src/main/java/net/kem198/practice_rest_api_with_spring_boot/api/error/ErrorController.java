package net.kem198.practice_rest_api_with_spring_boot.api.error;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/error")
public class ErrorController {
    @GetMapping
    public void getError() {
        throw new ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/detail")
    public void getErrorDetail() {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setType(URI.create("https://example.com"));
        problemDetail.setDetail("Customized error details");
        throw new ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR, problemDetail, null);
    }
}
