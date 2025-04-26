package dev.kem198.practice_spring_boot_rest_api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("FizzBuzz の変換リクエストを待ち受ける FizzBuzzController クラス")
public class FizzBuzzControllerTests {
    @Test
    @DisplayName("JUnit の動作確認用 成功")
    void checkTestSuccess() {
        assertEquals(1, 1);
    }

    @Nested
    @DisplayName("3 で割り切れる数値を渡した場合は文字列 \"Fizz\" を返す")
    class ReturnsFizzForMultiplesOf3 {
        @Test
        @DisplayName("3 を渡された場合は {\"result\": \"Fizz\"} を返す")
        void returnsFizzFor3() {
            // Arrange
            FizzBuzzController fizzBuzzController = new FizzBuzzController();

            // Act
            String actual = fizzBuzzController.execute("3");

            // Assert
            assertEquals("{\"result\": \"Fizz\"}", actual);
        }
    }
}
