package net.kem198.practice_rest_api_with_spring_boot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.kem198.practice_rest_api_with_spring_boot.dto.GreetingDto;

@SpringBootTest
public class GreetingServiceTests {
    @Autowired
    private GreetingService GreetingService;

    @Nested
    class ProcessGreetingMethodTests {
        @Test
        @DisplayName("文字列を与えると GreetingDto 型の結果を返す")
        void returnsGreetingDtoForString() {
            // Act
            GreetingDto greetingDto = GreetingService.processGreeting("World");

            // Assert
            assertEquals(new GreetingDto(1, "Hello, World!"), greetingDto);
        }
    }
}
