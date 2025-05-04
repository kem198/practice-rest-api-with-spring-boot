package net.kem198.practice_rest_api_with_spring_boot.domain.service.todo;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.inject.Inject;
import net.kem198.practice_rest_api_with_spring_boot.domain.model.Todo;
import net.kem198.practice_rest_api_with_spring_boot.domain.repository.todo.TodoRepository;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {
    private static final long MAX_UNFINISHED_COUNT = 5;

    @Inject
    TodoRepository todoRepository;

    @Override
    @Transactional(readOnly = true)
    public Collection<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo create(Todo todo) {
        long unfinishedCount = todoRepository.countByFinished(false);
        if (unfinishedCount >= MAX_UNFINISHED_COUNT) {
            throw new IllegalStateException(
                    String.format("[E001] The count of un-finished Todo must not be over %d.", MAX_UNFINISHED_COUNT));
        }

        String todoId = UUID.randomUUID().toString();
        Date createdAt = new Date();

        todo.setTodoId(todoId);
        todo.setCreatedAt(createdAt);
        todo.setFinished(false);

        todoRepository.create(todo);

        return todo;
    }

    @Override
    public Todo finish(String todoId) {
        Todo todo = findOne(todoId);
        if (todo.isFinished()) {
            throw new IllegalStateException(
                    String.format("[E002] The requested Todo is already finished. (id=%s)", todoId));
        }
        todo.setFinished(true);
        todoRepository.update(todo);
        return todo;
    }

    @Override
    public void delete(String todoId) {
        Todo todo = findOne(todoId);
        todoRepository.delete(todo);
    }

    private Todo findOne(String todoId) {
        Todo todo = todoRepository.findById(todoId);
        if (todo == null) {
            throw new IllegalArgumentException(
                    String.format("[E404] The requested Todo is not found. (id=%s)", todoId));
        }
        return todo;
    }
}
