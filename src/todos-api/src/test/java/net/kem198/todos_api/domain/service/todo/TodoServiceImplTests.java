package net.kem198.todos_api.domain.service.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.kem198.todos_api.domain.exception.common.ResourceNotFoundException;
import net.kem198.todos_api.domain.model.Todo;

@SpringBootTest
public class TodoServiceImplTests {
    @Autowired
    TodoService todoService;

    @Nested
    class FindOneTests {
        @Test
        @DisplayName("引数の todoId と一致する Todo を返す")
        public void returnsTodoForMatchingTodoId() {
            // Arrange
            Todo expectedTodo = new Todo();
            todoService.create(expectedTodo);

            // Act
            Todo todo = todoService.findOne(expectedTodo.getTodoId());

            // Assert
            assertEquals(expectedTodo, todo);
        }

        @Test
        @DisplayName("引数の todoId と一致する Todo が存在しなければ ResourceNotFoundException をスローする")
        public void throwsResourceNotFoundExceptionForNonMatchingTodoId() {
            // Act & Assert
            assertThrows(ResourceNotFoundException.class, () -> {
                todoService.findOne("1");
            });
        }

    }

}
