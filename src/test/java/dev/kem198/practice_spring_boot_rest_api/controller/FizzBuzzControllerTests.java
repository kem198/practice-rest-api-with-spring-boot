package dev.kem198.practice_spring_boot_rest_api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

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
}
