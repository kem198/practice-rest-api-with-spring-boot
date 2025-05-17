package net.kem198.todos_api.api.common.error;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
    class HandleSystemErrorTests {
        @Test
        @DisplayName("システムエラーは \"500 Internal Server Error\" で返す")
        void returns500InternalServerErrorWhenSystemError() throws JsonMappingException, JsonProcessingException {
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
        void notReturnsStackTraceWhenSystemError() throws JsonMappingException, JsonProcessingException {
            // Act
            ResponseEntity<String> response = restTemplate.getForEntity(
                    "/api/v1/error/throw-system-error",
                    String.class);

            // Assert
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            assertNull(responseBody.get("trace"));
        }
    }

    @Nested
    class HandleMethodArgumentNotValidTests {
        @Test
        @DisplayName("引数のバリデーションエラーは \"400 Bad Request\" で返す")
        void returns400BadRequestWhenMethodArgumentNotValidException()
                throws JsonMappingException, JsonProcessingException {
            // Arrange
            String requestBody = """
                        {
                            "todoDescription": "123456789012345678901234567890123456789012345678901"
                        }
                    """;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            // Act
            ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/todos", requestEntity, String.class);

            // Assert
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            assertEquals("Validation failed for the request.",
                    responseBody.get("detail").asText());
        }

        @Test
        @DisplayName("引数のバリデーションエラーの数だけ errors の配列を返す")
        void returnsErrorsForArgumentErrorTimes()
                throws JsonMappingException, JsonProcessingException {
            // Arrange
            String requestBody = """
                        {
                            "todoDescription": "123456789012345678901234567890123456789012345678901"
                        }
                    """;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            // Act
            ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/todos", requestEntity, String.class);

            // Assert
            // 配列の順序が保証されないため Set に変換して検証する
            Set<Map<String, Object>> expectedErrors = Set.of(
                    Map.of(
                            "objectName", "todoResource",
                            "field", "todoTitle",
                            "rejectedValue", "null",
                            "code", "NotNull",
                            "message", "must not be null"),
                    Map.of(
                            "objectName", "todoResource",
                            "field", "todoDescription",
                            "rejectedValue", "123456789012345678901234567890123456789012345678901",
                            "code", "Size",
                            "message", "size must be between 1 and 50"));

            JsonNode responseBody = objectMapper.readTree(response.getBody());
            Set<Map<String, String>> actualErrors = StreamSupport
                    .stream(responseBody.get("errors").spliterator(), false)
                    .map(error -> Map.of(
                            "objectName", error.get("objectName").asText(),
                            "field", error.get("field").asText(),
                            "rejectedValue",
                            error.get("rejectedValue").isNull() ? null : error.get("rejectedValue").asText(),
                            "code", error.get("code").asText(),
                            "message", error.get("message").asText()))
                    .collect(Collectors.toSet());

            assertEquals(expectedErrors, actualErrors);
        }
    }
}
