package dev.kem198.practice_spring_boot_rest_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FizzBuzzTests {

    private FizzBuzz fizzBuzz;

    @BeforeEach
    void BeforeEach() {
        fizzBuzz = new FizzBuzz();
    }

    @Test
    @DisplayName("1 を渡すと文字列 1 を返すこと")
    void shouldReturn1WhenPassed1() {
        assertEquals("1", fizzBuzz.convert(1));
    }

    @Test
    @DisplayName("2 を渡すと文字列 2 を返すこと")
    void shouldReturn2WhenPassed2() {
        assertEquals("2", fizzBuzz.convert(2));
    }

    @Test
    @DisplayName("3 を渡すと文字列 Fizz を返すこと")
    void shouldReturnFizzWhenPassed3() {
        assertEquals("Fizz", fizzBuzz.convert(3));
    }

    @Test
    @DisplayName("5 を渡すと文字列 Buzz を返すこと")
    void shouldReturnBuzzWhenPassed5() {
        assertEquals("Buzz", fizzBuzz.convert(5));
    }
}
