package net.kem198.practice_rest_api_with_spring_boot.api.todo;

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
public class TodoRestControllerTests {

    private ObjectMapper objectMapper;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Nested
    class PutTodoTests {
        @DisplayName("更新対象のリソースが存在しない場合は業務例外を返す")
        @Test
        void returnsBusinessExceptionWhenNotExistsTarget() throws Exception {
            // Act
            ResponseEntity<String> response = restTemplate.exchange(
                    "/api/v1/todos/abc",
                    org.springframework.http.HttpMethod.PUT,
                    null,
                    String.class);

            // Assert
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            assertEquals(
                    "[E002] [net.kem198.practice_rest_api_with_spring_boot.domain.service.todo.TodoServiceImpl] Resource is not found: abc",
                    responseBody.get("detail").asText());
        }
    }
}
