package ru.jabka.filmplus.model;

import java.util.ArrayList;
import java.util.List;

public class ApiError {

    private boolean success;
    private String message;

    public ApiError(final String message) {
        this.success = false;
        this.message = message;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }
}