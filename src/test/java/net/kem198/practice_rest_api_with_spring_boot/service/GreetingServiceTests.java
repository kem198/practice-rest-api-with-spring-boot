package net.kem198.practice_rest_api_with_spring_boot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GreetingServiceTests {
    @Autowired
    private GreetingService GreetingService;

    @Nested
    class ProcessGreetingMethodTests {
        @Test
        @DisplayName("数値を与えると Greeting の結果を返す")
        void returnsResultOfGreetingForNumber() {
            // Act
            String actual = GreetingService.processGreeting(3);

            // Assert
            assertEquals("{\"id\":1,\"content\":\"Hello, World!\"}", actual);
        }
    }
}
