package dev.kem198.practice_spring_boot_rest_api.controller;

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
    }

}
