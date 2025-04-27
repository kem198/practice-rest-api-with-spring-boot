package net.kem198.practice_rest_api_with_spring_boot.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.kem198.practice_rest_api_with_spring_boot.service.FizzBuzzService;

@RestController
public class FizzBuzzController {
    @Autowired
    private FizzBuzzService fizzBuzzService;

    @GetMapping("/fizzbuzz")
    public ResponseEntity<Map<String, String>> getFizzBuzz(
            @RequestParam(value = "num", required = true) String numberString) {
        try {
            String result = fizzBuzzService.processFizzBuzz(numberString);
            return ResponseEntity.ok(Map.of("result", result));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "error", "Invalid number format",
                            "message", "The 'num' query parameter must be a valid integer."));
        }
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, String>> handleMissingParams(MissingServletRequestParameterException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "error", "Missing required parameter",
                        "message", String.format("The '%s' query parameter is required.", ex.getParameterName())));
    }
}
