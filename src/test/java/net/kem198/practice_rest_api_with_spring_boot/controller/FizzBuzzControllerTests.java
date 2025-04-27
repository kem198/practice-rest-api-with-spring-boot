package net.kem198.practice_rest_api_with_spring_boot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FizzBuzzControllerTests {

    private ObjectMapper objectMapper;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Nested
    class GetRequestTests {
        @Nested
        @DisplayName("3 で割り切れる数値を渡した場合は文字列 \"Fizz\" を返す")
        class ReturnsFizzForMultiplesOf3 {
            @Test
            @DisplayName("\"?num=3\" でリクエストされた場合は {\"result\": \"Fizz\"} を返す")
            void returnsFizzFor3() throws Exception {
                // Act
                ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/fizzbuzz?num=3", String.class);

                // Assert
                assertEquals(HttpStatus.OK, response.getStatusCode());
                JsonNode responseBody = objectMapper.readTree(response.getBody());
                assertEquals("Fizz", responseBody.get("result").asText());
            }
        }

        @Nested
        @DisplayName("5 で割り切れる数値を渡した場合は文字列 \"Buzz\" を返す")
        class ReturnsBuzzForMultiplesOf5 {
            @Test
            @DisplayName("\"?num=5\" でリクエストされた場合は {\"result\": \"Buzz\"} を返す")
            void returnsBuzzFor5() throws Exception {
                // Act
                ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/fizzbuzz?num=5", String.class);

                // Assert
                assertEquals(HttpStatus.OK, response.getStatusCode());
                JsonNode responseBody = objectMapper.readTree(response.getBody());
                assertEquals("Buzz", responseBody.get("result").asText());
            }
        }

        @Nested
        @DisplayName("3 かつ 5 で割り切れる数値を渡した場合は文字列 \"FizzBuzz\" を返す")
        class ReturnsFizzBuzzForMultiplesOf3and5 {
            @Test
            @DisplayName("\"?num=15\" でリクエストされた場合は {\"result\": \"FizzBuzz\"} を返す")
            void returnsFizzBuzzFor15() throws Exception {
                // Act
                ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/fizzbuzz?num=15", String.class);

                // Assert
                assertEquals(HttpStatus.OK, response.getStatusCode());
                JsonNode responseBody = objectMapper.readTree(response.getBody());
                assertEquals("FizzBuzz", responseBody.get("result").asText());
            }
        }

        @Nested
        @DisplayName("どれでもない数値文字列を渡した場合はそのまま返す")
        class ReturnsNumberStringForOthers {
            @Test
            @DisplayName("\"?num=1\" でリクエストされた場合は {\"result\": \"1\"} を返す")
            void returns1For1() throws Exception {
                // Act
                ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/fizzbuzz?num=1", String.class);

                // Assert
                assertEquals(HttpStatus.OK, response.getStatusCode());
                JsonNode responseBody = objectMapper.readTree(response.getBody());
                assertEquals("1", responseBody.get("result").asText());
            }
        }

        @Nested
        @DisplayName("数値文字列を渡されなかったらエラーレスポンスを返す")
        class ReturnsErrorResponseForMissingNumberString {
            @Test
            @DisplayName("パラメータ無しでリクエストされた場合は所定のエラーレスポンスを返す")
            void returnsErrorResponseForMissingParameter() throws Exception {
                // Act
                ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/fizzbuzz", String.class);

                // Assert
                assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
                JsonNode responseBody = objectMapper.readTree(response.getBody());
                assertEquals("Missing required parameter", responseBody.get("error").asText());
                assertEquals("The 'num' query parameter is required.", responseBody.get("message").asText());
            }

            @Test
            @DisplayName("数値として扱えない値でリクエストされた場合は所定のエラーレスポンスを返す")
            void returnsErrorResponseForInvalidNumber() throws Exception {
                // Act
                ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/fizzbuzz?num=abc", String.class);

                // Assert
                assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
                JsonNode responseBody = objectMapper.readTree(response.getBody());
                assertEquals("Invalid number format", responseBody.get("error").asText());
                assertEquals("The 'num' query parameter must be a valid integer.",
                        responseBody.get("message").asText());
            }
        }
    }
}
