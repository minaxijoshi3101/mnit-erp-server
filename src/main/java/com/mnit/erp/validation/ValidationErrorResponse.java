package com.mnit.erp.validation;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {

    private HttpStatus httpStatus;
    private LocalDateTime timestamp;
    private List<Violation> violations = new ArrayList<>();

    public List<Violation> getViolations() {
        return violations;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setHttpStatus(HttpStatus httpStatus) { this.httpStatus = httpStatus;};
    public void setTimestamp() { this.timestamp = LocalDateTime.now();}
    public void setViolations(List<Violation> violations) {
        this.violations = violations;
    }
}