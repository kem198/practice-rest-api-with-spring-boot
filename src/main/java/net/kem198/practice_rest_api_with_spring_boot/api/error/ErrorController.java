package net.kem198.practice_rest_api_with_spring_boot.api.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/error")
public class ErrorController {
    @GetMapping
    public void error() {
        throw new ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
