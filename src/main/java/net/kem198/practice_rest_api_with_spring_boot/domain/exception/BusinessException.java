package net.kem198.practice_rest_api_with_spring_boot.domain.exception;

import org.springframework.http.ProblemDetail;

public class BusinessException extends RuntimeException {
    private final ProblemDetail problemDetail;

    public BusinessException(ProblemDetail problemDetail) {
        super(problemDetail.getDetail());
        this.problemDetail = problemDetail;
    }

    public ProblemDetail getProblemDetail() {
        return problemDetail;
    }
}
