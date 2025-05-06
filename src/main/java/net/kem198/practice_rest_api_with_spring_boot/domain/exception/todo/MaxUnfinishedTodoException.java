package net.kem198.practice_rest_api_with_spring_boot.domain.exception.todo;

import org.springframework.http.HttpStatus;

import net.kem198.practice_rest_api_with_spring_boot.domain.exception.common.BusinessException;

public class MaxUnfinishedTodoException extends BusinessException {
    public MaxUnfinishedTodoException(String resourceName, long maxCount) {
        super(HttpStatus.BAD_REQUEST,
                "[E001] [" + resourceName + "] The count of un-finished Todo must not be over: " + maxCount);
    }
}
