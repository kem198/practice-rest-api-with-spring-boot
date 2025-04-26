package dev.kem198.practice_spring_boot_rest_api.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FizzBuzzController {

    public String execute(@RequestParam(value = "num") String num) {
        return "{\"result\": \"Fizz\"}";
    }

}
