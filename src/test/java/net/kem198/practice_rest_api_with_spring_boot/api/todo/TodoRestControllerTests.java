package net.kem198.practice_rest_api_with_spring_boot.api.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
    class PostTodoTests {
        @DisplayName("正常なボディの場合 201 と所定の正常レスポンスを返す")
        @Test
        void returnsCreatedWithResponsesForValidBody() throws Exception {
            // Arrange
            String requestBody = """
                        {
                            "todoTitle": "Hello World!",
                            "todoDescription": "Hello Todo Description!"
                        }
                    """;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            // Act
            ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/todos", requestEntity, String.class);

            // Assert
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            assertEquals("Hello World!", responseBody.get("todoTitle").asText());
            assertEquals("Hello Todo Description!", responseBody.get("todoDescription").asText());
            assertEquals(false, responseBody.get("finished").asBoolean());
            assertNotNull(responseBody.get("todoId").asText());
            assertNotNull(responseBody.get("createdAt").asText());
        }

        @DisplayName("todoTitle の文字数が 31 以上の場合は業務例外を返す")
        @Test
        void returnsBusinessExceptionWhenTodoTitleOvered() throws Exception {
            // Arrange
            String requestBody = """
                        {
                            "todoTitle": "1234567890123456789012345678901",
                            "todoDescription": "Hello Todo Description!"
                        }
                    """;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            // Act
            ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/todos", requestEntity, String.class);

            // Assert
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            assertEquals("Invalid request content.", responseBody.get("detail").asText());
        }
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
