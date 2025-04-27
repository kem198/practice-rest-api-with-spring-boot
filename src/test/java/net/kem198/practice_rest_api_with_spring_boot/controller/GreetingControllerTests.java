package net.kem198.practice_rest_api_with_spring_boot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.web.client.TestRestTemplate;

import net.kem198.practice_rest_api_with_spring_boot.model.Greeting;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("GreetingController の統合テスト")
public class GreetingControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("デフォルトの名前で挨拶を返す")
    void returnsGreetingWithDefaultName() {
        // Act
        ResponseEntity<Greeting> response = restTemplate.getForEntity("/api/v1/greeting", Greeting.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello, World!", response.getBody().content());
    }

    @Test
    @DisplayName("指定された名前で挨拶を返す")
    void returnsGreetingWithSpecifiedName() {
        // Act
        ResponseEntity<Greeting> response = restTemplate.getForEntity("/api/v1/greeting?name=KeM198", Greeting.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello, KeM198!", response.getBody().content());
    }
}
