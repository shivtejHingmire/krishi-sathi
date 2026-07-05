package com.shivar.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    /**
     * Indicates whether the API request was successful.
     */
    private boolean success;

    /**
     * Human-readable response message.
     */
    private String message;

    /**
     * The actual response payload.
     */
    private T data;

    /**
     * List of validation or business errors.
     * This will be populated only when success is false.
     */
    private List<String> errors;

    /**
     * Time when the response was generated.
     */
    @Builder.Default
    private Instant timestamp = Instant.now();
}