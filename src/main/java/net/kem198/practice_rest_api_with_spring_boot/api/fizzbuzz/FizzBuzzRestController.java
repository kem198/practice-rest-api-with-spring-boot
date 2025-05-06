package net.kem198.practice_rest_api_with_spring_boot.api.fizzbuzz;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.kem198.practice_rest_api_with_spring_boot.domain.service.fizzbuzz.FizzBuzzService;

@RestController
@RequestMapping("/api/v1/fizzbuzz")
public class FizzBuzzRestController {
    @Autowired
    private FizzBuzzService fizzBuzzService;

    @GetMapping("/convert")
    public ResponseEntity<Map<String, String>> convert(@RequestParam(value = "num") int number) {
        String result = fizzBuzzService.processFizzBuzz(number);
        return ResponseEntity.ok(Map.of("result", result));
    }
}
