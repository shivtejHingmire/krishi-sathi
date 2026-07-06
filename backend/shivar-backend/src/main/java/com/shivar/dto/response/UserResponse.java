package com.shivar.dto.response;

import com.shivar.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Response DTO representing a user.
 * Exposes only safe information to API consumers.
 */
@Getter
@Setter
public class UserResponse {

    private Long id;

    private String fullName;

    private String mobileNumber;

    private String email;

    private UserStatus status;

    private Boolean mobileVerified;

    private Boolean emailVerified;

    private LocalDateTime createdAt;

}