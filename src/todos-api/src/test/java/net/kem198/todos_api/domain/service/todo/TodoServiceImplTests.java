package net.kem198.todos_api.domain.service.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.kem198.todos_api.domain.exception.common.ResourceNotFoundException;
import net.kem198.todos_api.domain.exception.todo.AlreadyFinishedTodoException;
import net.kem198.todos_api.domain.exception.todo.MaxUnfinishedTodoException;
import net.kem198.todos_api.domain.model.Todo;
import net.kem198.todos_api.domain.repository.todo.TodoRepository;

@SpringBootTest
public class TodoServiceImplTests {
    @Autowired
    TodoRepository todoRepository;

    @Autowired
    TodoService todoService;

    @BeforeEach
    void setUp() {
        todoRepository.findAll().forEach((todo) -> todoRepository.delete(todo));
    }

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
            assertEquals(expectedTodo.getTodoId(), todo.getTodoId());
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

    @Nested
    class FindAllTests {
        @Test
        @DisplayName("Todo を Collection 形式で複数返す")
        public void returnsTodoCollection() {
            // Arrange
            Todo expectedTodo1 = new Todo();
            Todo expectedTodo2 = new Todo();
            todoService.create(expectedTodo1);
            todoService.create(expectedTodo2);
            Collection<Todo> expectedTodoCollection = new ArrayList<Todo>();
            expectedTodoCollection.add(expectedTodo1);
            expectedTodoCollection.add(expectedTodo2);

            // Act
            Collection<Todo> todoCollection = todoService.findAll();

            // Assert
            assertTrue(todoCollection.containsAll(expectedTodoCollection));
            assertEquals(expectedTodoCollection.size(), todoCollection.size());
        }
    }

    @Nested
    class CreateTests {
        @Test
        @DisplayName("Todo を登録する")
        public void registersTodo() {
            // Arrange
            Todo expectedTodo = new Todo();

            // Act
            todoService.create(expectedTodo);

            // Assert
            assertEquals(expectedTodo.getTodoId(), todoService.findOne(expectedTodo.getTodoId()).getTodoId());
        }

        @Test
        @DisplayName("未完了 Todo の数が上限に達しているなら MaxUnfinishedTodoException をスローしてタスクを作成しない")
        public void throwsMaxUnfinishedTodoExceptionAndNotRegisterTodoWhenUnfinishedTasksReachLimit() {
            // Arrange
            final long MAX_UNFINISHED_COUNT = 5;

            for (int i = 0; i < MAX_UNFINISHED_COUNT; i++) {
                Todo todo = new Todo();
                todo.setFinished(true);
                todoService.create(todo);
            }
            Todo newTodo = new Todo();

            // Act & Assert
            assertThrows(MaxUnfinishedTodoException.class, () -> {
                todoService.create(newTodo);
            });
            assertTrue(todoService.findAll().size() == MAX_UNFINISHED_COUNT);
        }
    }

    @Nested
    class FinishTests {
        @Test
        @DisplayName("引数の todoId と一致する Todo を完了にする")
        public void finishTodoForMatchingTodoId() {
            // Arrange
            Todo targetTodo = new Todo();
            todoService.create(targetTodo);

            // Act
            todoService.finish(targetTodo.getTodoId());

            // Assert
            assertTrue(targetTodo.isFinished());
        }

        @Test
        @DisplayName("指定した Todo が既に完了済みであれば AlreadyFinishedTodoException をスローする")
        public void throwsAlreadyFinishedTodoExceptionWhenTodoIsAlreadyFinished() {
            // Arrange
            Todo targetTodo = new Todo();
            todoService.create(targetTodo);
            todoService.finish(targetTodo.getTodoId());

            // Act & Assert
            assertThrows(AlreadyFinishedTodoException.class, () -> {
                todoService.finish(targetTodo.getTodoId());
            });
        }
    }

    @Nested
    class DeleteTests {
        @Test
        @DisplayName("引数の todoId と一致する Todo を削除する")
        public void deleteTodoForMatchingTodoId() {
            // Arrange
            Todo todo = new Todo();
            todoService.create(todo);

            // Act
            todoService.delete(todo.getTodoId());

            // Assert
            assertFalse(todoService.findAll().contains(todo));
        }
    }
}
