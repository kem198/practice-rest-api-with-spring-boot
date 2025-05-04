package net.kem198.practice_rest_api_with_spring_boot.domain.service.todo;

import java.util.Collection;

import net.kem198.practice_rest_api_with_spring_boot.domain.model.Todo;

public interface TodoService {
    Collection<Todo> findAll();

    Todo create(Todo todo);

    Todo finish(String todoId);

    void delete(String todoId);
}
