package dev.kem198.practice_spring_boot_rest_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FizzBuzzTests {

    @Test
    @DisplayName("1 を渡すと 1 が返ること")
    void shouldReturn1WhenPassed1() {
        // Arrange
        FizzBuzz fizzbuzz = new FizzBuzz();

        // Act & Assert
        assertEquals("1", fizzbuzz.convert(1));
    }

    @Test
    @DisplayName("2 を渡すと 2 が返ること")
    void shouldReturn2WhenPassed2() {
        // Arrange
        FizzBuzz fizzbuzz = new FizzBuzz();

        // Act & Assert
        assertEquals("2", fizzbuzz.convert(2));
    }
}
