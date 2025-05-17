package net.kem198.practice_rest_api_with_spring_boot.api.todo;

import org.mapstruct.Mapper;

import net.kem198.practice_rest_api_with_spring_boot.domain.model.Todo;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    TodoResource map(Todo todo);

    Todo map(TodoResource todoResource);
}
