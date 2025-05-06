package net.kem198.practice_rest_api_with_spring_boot.api.fizzbuzz;

import org.mapstruct.Mapper;

import net.kem198.practice_rest_api_with_spring_boot.domain.model.Todo;

@Mapper(componentModel = "spring")
public interface FizBuzzMapper {

    FizzBuzzResource map(String result);

    Todo map(FizzBuzzResource fizzBuzzResource);
}
