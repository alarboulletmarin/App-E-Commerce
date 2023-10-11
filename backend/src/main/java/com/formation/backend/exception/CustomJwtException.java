package com.formation.backend.exception;

public class CustomJwtException extends RuntimeException {
    private final String reason;

    public CustomJwtException(String reason, String message) {
        super(message);
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
