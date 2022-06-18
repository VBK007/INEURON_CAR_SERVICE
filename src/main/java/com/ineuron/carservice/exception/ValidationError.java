package com.ineuron.carservice.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidationError {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> errors = new ArrayList<>();

    private final String message;

    public ValidationError(final String message) {
        this.message = message;
    }

    public void addValidationError(final String error) {
        errors.add(error);
    }

}
