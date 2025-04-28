package net.kem198.practice_rest_api_with_spring_boot.controller;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import net.kem198.practice_rest_api_with_spring_boot.constants.ErrorTitles;
import net.kem198.practice_rest_api_with_spring_boot.service.FizzBuzzService;

@RestController
@RequestMapping("/api/v1/fizzbuzz")
public class FizzBuzzController {
    @Autowired
    private FizzBuzzService fizzBuzzService;

    @GetMapping
    public ResponseEntity<Map<String, String>> getFizzBuzz(@RequestParam(value = "num") int number) {
        String result = fizzBuzzService.processFizzBuzz(number);
        return ResponseEntity.ok(Map.of("result", result));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(ErrorTitles.INVALID_NUMBER_FORMAT.getTitle());
        problemDetail.setDetail(String.format("The '%s' query parameter must be a valid integer.", ex.getName()));
        problemDetail.setInstance(URI.create("/api/v1/fizzbuzz"));
        return problemDetail;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ProblemDetail handleMissingParams(MissingServletRequestParameterException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(ErrorTitles.MISSING_PARAMETER.getTitle());
        problemDetail.setDetail(String.format("The '%s' query parameter is required.", ex.getParameterName()));
        problemDetail.setInstance(URI.create("/api/v1/fizzbuzz"));
        return problemDetail;
    }
}
