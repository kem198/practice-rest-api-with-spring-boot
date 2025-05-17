package net.kem198.todos_api.api.fizzbuzz;

import org.mapstruct.Mapper;

import net.kem198.todos_api.domain.model.Todo;

@Mapper(componentModel = "spring")
public interface FizBuzzMapper {

    FizzBuzzResource map(String result);

    Todo map(FizzBuzzResource fizzBuzzResource);
}
