package net.kem198.practice_rest_api_with_spring_boot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import net.kem198.practice_rest_api_with_spring_boot.controller.FizzBuzzController;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(FizzBuzzController.class)
@DisplayName("FizzBuzz の変換リクエストを待ち受ける FizzBuzzController クラス")
public class FizzBuzzControllerTests {
    @Test
    @DisplayName("JUnit の動作確認用 成功")
    void checkTestSuccess() {
        assertEquals(1, 1);
    }

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("3 で割り切れる数値を渡した場合は文字列 \"Fizz\" を返す")
    class ReturnsFizzForMultiplesOf3 {
        @Test
        @DisplayName("\"?num=3\" でリクエストされた場合は {\"result\": \"Fizz\"} を返す")
        void returnsFizzFor3() throws Exception {
            // Act
            ResultActions resultActions = mockMvc
                    .perform(get("/fizzbuzz").param("num", "3"));

            // Assert
            resultActions
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.result").value("Fizz"));
        }
    }

    @Nested
    @DisplayName("5 で割り切れる数値を渡した場合は文字列 \"Buzz\" を返す")
    class ReturnsBuzzForMultiplesOf5 {
        @Test
        @DisplayName("\"?num=5\" でリクエストされた場合は {\"result\": \"Buzz\"} を返す")
        void returnsBuzzFor5() throws Exception {
            // Act
            ResultActions resultActions = mockMvc
                    .perform(get("/fizzbuzz").param("num", "5"));

            // Assert
            resultActions
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.result").value("Buzz"));
        }
    }

    @Nested
    @DisplayName("3 かつ 5 で割り切れる数値を渡した場合は文字列 \"FizzBuzz\" を返す")
    class ReturnsFizzBuzzForMultiplesOf3and5 {
        @Test
        @DisplayName("\"?num=15\" でリクエストされた場合は {\"result\": \"FizzBuzz\"} を返す")
        void returnsFizzBuzzFor15() throws Exception {
            // Act
            ResultActions resultActions = mockMvc
                    .perform(get("/fizzbuzz").param("num", "15"));

            // Assert
            resultActions
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.result").value("FizzBuzz"));
        }
    }

    @Nested
    @DisplayName("どれでもない数値文字列を渡した場合はそのまま返す")
    class ReturnsNumberStringForOthers {
        @Test
        @DisplayName("\"?num=1\" でリクエストされた場合は {\"result\": \"1\"} を返す")
        void returns1For1() throws Exception {
            // Act
            ResultActions resultActions = mockMvc
                    .perform(get("/fizzbuzz").param("num", "1"));

            // Assert
            resultActions
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.result").value("1"));
        }
    }

    @Nested
    @DisplayName("数値を渡されなかったらエラーレスポンスを返す")
    class ReturnsErrorResponseForMissingNumberString {
        @Test
        @DisplayName("パラメータ無しでリクエストされた場合は所定のエラーレスポンスを返す")
        void returnsErrorResponseForMissingParameter() throws Exception {
            // Act
            ResultActions resultActions = mockMvc
                    .perform(get("/fizzbuzz"));

            // Assert
            resultActions
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.error").value("Missing required parameter"))
                    .andExpect(jsonPath("$.message").value("The 'num' query parameter is required."));
        }

        @Test
        @DisplayName("数値として扱えない値でリクエストされた場合は所定のエラーレスポンスを返す")
        void returnsErrorResponseForInvalidNumber() throws Exception {
            // Act
            ResultActions resultActions = mockMvc
                    .perform(get("/fizzbuzz").param("num", "abc"));

            // Assert
            resultActions
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.error").value("Invalid number format"))
                    .andExpect(jsonPath("$.message").value("The 'num' query parameter must be a valid integer."));
        }
    }
}
