package dev.kem198.practice_spring_boot_rest_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Fizz Buzz 数列と変換規則を扱う FizzBuzz クラス")
public class FizzBuzzTests {
    private FizzBuzz fizzBuzz;

    @BeforeEach
    void BeforeEach() {
        fizzBuzz = new FizzBuzz();
    }

    @Nested
    @DisplayName("Convert メソッドは数を文字列に変換する")
    class ConvertMethodIsConvertToStringFromNum {

        @Nested
        @DisplayName("3 の倍数のときは数の代わりに「Fizz」に変換する")
        class ConvertToFizzWhenPassedMultipleBy3 {
            @Test
            @DisplayName("3 を渡すと文字列 Fizz を返すこと")
            void shouldReturnFizzWhenPassed3() {
                assertEquals("Fizz", fizzBuzz.convert(3));
            }
        }

        @Nested
        @DisplayName("5 の倍数のときは数の代わりに「Fizz」に変換する")
        class ConvertToBuzzWhenPassedMultipleBy5 {
            @Test
            @DisplayName("5 を渡すと文字列 Buzz を返すこと")
            void shouldReturnBuzzWhenPassed5() {
                assertEquals("Buzz", fizzBuzz.convert(5));
            }
        }

        @Nested
        @DisplayName("3 と 5 両方の倍数のときは数の代わりに「FizzBuzz」に変換する")
        class ConvertToFizzBuzzWhenPassedMultipleBy3and5 {
            @Test
            @DisplayName("15 を渡すと文字列 FizzBuzz を返すこと")
            void shouldReturnFizzBuzzWhenPassed15() {
                assertEquals("FizzBuzz", fizzBuzz.convert(15));
            }
        }

        @Nested
        @DisplayName("その他の数のときはそのまま文字列に変換する")
        class ConvertToStringFromOthers {
            @Test
            @DisplayName("1 を渡すと文字列 1 を返すこと")
            void shouldReturn1WhenPassed1() {
                assertEquals("1", fizzBuzz.convert(1));
            }
        }
    }
}
