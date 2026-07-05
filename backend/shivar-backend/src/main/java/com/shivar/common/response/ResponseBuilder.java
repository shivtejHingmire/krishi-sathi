package com.shivar.common.response;

import java.util.List;

public final class ResponseBuilder {

    /**
     * Private constructor to prevent object creation.
     */
    private ResponseBuilder() {
    }

    /**
     * Creates a successful API response with data.
     */
    public static <T> ApiResponse<T> success(String message, T data) {

        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    /**
     * Creates a successful API response without data.
     */
    public static ApiResponse<Void> success(String message) {

        return ApiResponse.<Void>builder()
                .success(true)
                .message(message)
                .build();
    }

    /**
     * Creates a failed API response.
     */
    public static ApiResponse<Void> failure(String message) {

        return ApiResponse.<Void>builder()
                .success(false)
                .message(message)
                .build();
    }

    /**
     * Creates a failed API response with validation errors.
     */
    public static ApiResponse<Void> failure(String message, List<String> errors) {

        return ApiResponse.<Void>builder()
                .success(false)
                .message(message)
                .errors(errors)
                .build();
    }
}