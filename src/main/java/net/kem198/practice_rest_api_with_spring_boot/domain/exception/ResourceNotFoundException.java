package net.kem198.practice_rest_api_with_spring_boot.domain.exception;

import org.springframework.http.ProblemDetail;

public class ResourceNotFoundException extends RuntimeException {
    private final ProblemDetail problemDetail;

    public ResourceNotFoundException(ProblemDetail problemDetail) {
        super(problemDetail.getDetail());
        this.problemDetail = problemDetail;
    }

    public ProblemDetail getProblemDetail() {
        return problemDetail;
    }
}
