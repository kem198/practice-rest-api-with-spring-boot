package net.kem198.practice_rest_api_with_spring_boot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FizzBuzzServiceTests {
    @Autowired
    private FizzBuzzService fizzBuzzService;

    @Test
    @DisplayName("FizzBuzzUtils.convert() を一回呼び結果を返す")
    void returnsResultForNumericString() {
        // Act
        String actual = fizzBuzzService.processFizzBuzz(3);

        // Assert
        assertEquals("Fizz", actual);
    }
}
