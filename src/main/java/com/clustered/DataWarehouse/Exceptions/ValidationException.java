package com.clustered.DataWarehouse.Exceptions;

import lombok.Getter;

import java.util.List;
@Getter
public class ValidationException extends RuntimeException {
    private final List<String> errors;

    public ValidationException(List<String> errors) {
        super("Validation failed");
        this.errors = errors;
    }
}
