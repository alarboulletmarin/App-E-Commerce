package com.formation.backend.exception;

import lombok.Getter;

@Getter
public class CustomJwtException extends RuntimeException {
    private final String reason;

    public CustomJwtException(String reason, String message) {
        super(message);
        this.reason = reason;
    }

}
