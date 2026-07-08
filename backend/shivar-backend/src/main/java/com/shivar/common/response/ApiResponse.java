package com.shivar.common.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    /**
     * Indicates whether the request was successful.
     */
    private boolean success;

    /**
     * Human-readable response message.
     */
    private String message;

    /**
     * Actual response payload.
     */
    private T data;

    /**
     * Validation or business errors.
     */
    private List<String> errors;

    /**
     * Timestamp when the response was generated.
     */
    @Builder.Default
    private Instant timestamp = Instant.now();
}