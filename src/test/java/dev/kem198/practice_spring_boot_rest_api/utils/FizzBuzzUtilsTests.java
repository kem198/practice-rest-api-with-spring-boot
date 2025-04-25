package dev.kem198.practice_spring_boot_rest_api.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Fizz Buzz 数列と変換規則を扱う FizzBuzzUtils クラス")
public class FizzBuzzUtilsTests {
    @Nested
    @DisplayName("Convert メソッドは数を文字列に変換する")
    class ConvertMethod {
        @Nested
        @DisplayName("3 の倍数の場合は「Fizz」に変換する")
        class ConvertsMultiplesOf3ToFizz {
            @Test
            @DisplayName("3 を渡すと文字列 Fizz を返す")
            void returnsFizzFor3() {
                assertEquals("Fizz", FizzBuzzUtils.convert(3));
            }
        }

        @Nested
        @DisplayName("5 の倍数の場合は「Fizz」に変換する")
        class ConvertsMultiplesOf5ToBuzz {
            @Test
            @DisplayName("5 を渡すと文字列 Buzz を返す")
            void returnsBuzzFor5() {
                assertEquals("Buzz", FizzBuzzUtils.convert(5));
            }
        }

        @Nested
        @DisplayName("3 と 5 両方の倍数の場合は「FizzBuzz」に変換する")
        class ConvertsMultiplesOf3And5ToFizzBuzz {
            @Test
            @DisplayName("15 を渡すと文字列 FizzBuzz を返す")
            void returnsFizzBuzzFor15() {
                assertEquals("FizzBuzz", FizzBuzzUtils.convert(15));
            }
        }

        @Nested
        @DisplayName("その他の数の場合はそのまま文字列に変換する")
        class ConvertsOtherNumbersToString {
            @Test
            @DisplayName("1 を渡すと文字列 1 を返す")
            void returns1For1() {
                assertEquals("1", FizzBuzzUtils.convert(1));
            }
        }
    }
}
