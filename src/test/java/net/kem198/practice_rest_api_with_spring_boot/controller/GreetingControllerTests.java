package net.kem198.practice_rest_api_with_spring_boot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GreetingControllerTests {
    @Test
    @DisplayName("JUnit の動作確認用 成功")
    void checkTestSuccess() {
        assertEquals(1, 1);
    }

    // @Test
    // @DisplayName("JUnit の動作確認用 失敗")
    // void checkTestFail() {
    // assertEquals(1, 0);
    // }
}
