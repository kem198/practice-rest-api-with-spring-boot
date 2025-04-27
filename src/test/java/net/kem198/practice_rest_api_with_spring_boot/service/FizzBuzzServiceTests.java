package net.kem198.practice_rest_api_with_spring_boot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FizzBuzzServiceTests {
    @Test
    @DisplayName("数値文字列であれば FizzBuzzUtils.convert() を一回呼び結果を返す")
    void returnsResultForNumericString() {
        // Arrange
        FizzBuzzService fizzBuzzService = new FizzBuzzService();

        // Act
        String actual = fizzBuzzService.processFizzBuzz("3");

        // Assert
        assertEquals("Fizz", actual);
    }

    @Test
    @DisplayName("数値文字列でなければ例外 IllegalArgumentException をスローする")
    void throwIllegalArgumentExceptionNonNumericString() {
        // Arrange
        FizzBuzzService fizzBuzzService = new FizzBuzzService();

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            fizzBuzzService.processFizzBuzz("abc");
        });

        // Assert
        // assertThrows() は継承元クラスが一致していればパスしてしまうので例外クラスを明示的にチェックする
        assertEquals(IllegalArgumentException.class, exception.getClass());
    }
}
