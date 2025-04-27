package net.kem198.practice_rest_api_with_spring_boot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FizzBuzzServiceTests {
    @Test
    public void a() {
        assertEquals("1", "1");
    }

    @Test
    @DisplayName("数値へ変換可能な文字列であれば FizzBuzzUtils.convert() を一回呼び結果を返す")
    void returnsResultForNumericString() {
        // Arrange
        FizzBuzzService fizzBuzzService = new FizzBuzzService();

        // Act
        String actual = fizzBuzzService.processFizzBuzz("3");

        // Assert
        assertEquals("Fizz", actual);
    }
}
