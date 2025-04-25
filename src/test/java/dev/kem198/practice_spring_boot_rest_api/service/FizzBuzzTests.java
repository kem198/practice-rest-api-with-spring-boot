package dev.kem198.practice_spring_boot_rest_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FizzBuzzTests {

    @Test
    @DisplayName("1 を渡すと 1 が返ること")
    void shouldReturn1WhenPassed1() {
        // Arrange (準備)
        FizzBuzz fizzbuzz = new FizzBuzz(); // ★3 ここから書く！「準備」から書く！

        // Act (実行)
        // ★2 使う側の視点でメソッド名を考える！
        String actual = fizzbuzz.convert(1);

        // Assert (検証)
        // ★1 ここから書く！「期待値」から書く！
        assertEquals("1", actual);
    }

}
