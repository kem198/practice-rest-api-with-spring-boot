package net.kem198.practice_rest_api_with_spring_boot.api.common.error;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestGlobalExceptionHandlerTests {

    private ObjectMapper objectMapper;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Nested
    class handleSystemErrorTests {
        @Test
        @DisplayName("システムエラーは 500 と固定メッセージで返す")
        void returns500andDetailWhenSystemError() throws Exception {
            // Act
            ResponseEntity<String> response = restTemplate.getForEntity(
                    "/api/v1/error/throw-system-error",
                    String.class);

            // Assert
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            assertEquals("An unexpected error occurred. Please contact support if the problem persists.",
                    responseBody.get("detail").asText());
        }

        @Test
        @DisplayName("システムエラーが発生したときはスタックトレースを返さない")
        void returnsStackTraceWhenSystemError() throws Exception {
            // Act
            ResponseEntity<String> response = restTemplate.getForEntity(
                    "/api/v1/error/throw-system-error",
                    String.class);

            // Assert
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            assertNull(responseBody.get("trace"));
        }
    }
}
