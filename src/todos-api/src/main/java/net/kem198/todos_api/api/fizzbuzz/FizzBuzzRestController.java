package net.kem198.todos_api.api.fizzbuzz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import net.kem198.todos_api.domain.service.fizzbuzz.FizzBuzzService;

@RestController
@RequestMapping("/api/v1/fizzbuzz")
public class FizzBuzzRestController {
    @Autowired
    private FizzBuzzService fizzBuzzService;

    @Autowired
    private FizBuzzMapper fizzBuzzMapper;

    @GetMapping("/convert")
    public ResponseEntity<FizzBuzzResource> convert(@RequestParam(value = "num") int number) {
        String result = fizzBuzzService.processFizzBuzz(number);
        FizzBuzzResource fizzBuzzResource = fizzBuzzMapper.map(result);
        return ResponseEntity.ok(fizzBuzzResource);
    }
}
