package net.kem198.practice_rest_api_with_spring_boot.domain.exception.common;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException(String resourceName, Object resourceId) {
        super(HttpStatus.NOT_FOUND, "[E002] [" + resourceName + "] Resource is not found: " + resourceId);
    }
}
