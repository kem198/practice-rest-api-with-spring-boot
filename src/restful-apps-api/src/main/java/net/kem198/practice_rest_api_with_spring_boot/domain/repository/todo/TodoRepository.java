package net.kem198.practice_rest_api_with_spring_boot.domain.repository.todo;

import java.util.Collection;

import net.kem198.practice_rest_api_with_spring_boot.domain.model.Todo;

public interface TodoRepository {
    Todo findById(String todoId);

    Collection<Todo> findAll();

    void create(Todo todo);

    boolean update(Todo todo);

    void delete(Todo todo);

    long countByFinished(boolean finished);
}
