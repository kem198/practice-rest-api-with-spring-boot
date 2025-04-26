package dev.kem198.practice_spring_boot_rest_api.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.kem198.practice_spring_boot_rest_api.utils.FizzBuzzUtils;

@RestController
public class FizzBuzzController {

    @GetMapping("/fizzbuzz")
    public String execute(@RequestParam(value = "num") String numberString) {
        int num;
        num = Integer.parseInt(numberString);

        String result = FizzBuzzUtils.convert(num);
        return String.format("{\"result\": \"%s\"}", result);

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, String>> handleMissingParams(MissingServletRequestParameterException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "error", "Missing required parameter",
                        "message", String.format("The '%s' query parameter is required.", ex.getParameterName())));
    }
}
