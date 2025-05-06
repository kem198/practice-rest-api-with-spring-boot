package net.kem198.practice_rest_api_with_spring_boot.api.fizzbuzz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import net.kem198.practice_rest_api_with_spring_boot.constants.ErrorTitles;
import net.kem198.practice_rest_api_with_spring_boot.domain.service.fizzbuzz.FizzBuzzService;

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

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ProblemDetail> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(ErrorTitles.INVALID_NUMBER_FORMAT.getTitle());
        problemDetail.setDetail(String.format("The '%s' query parameter must be a valid integer.", ex.getName()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }
}
