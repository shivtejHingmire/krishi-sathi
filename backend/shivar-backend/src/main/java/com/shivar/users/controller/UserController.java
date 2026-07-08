package com.shivar.users.controller;

import com.shivar.common.constants.ApiConstants;
import com.shivar.common.constants.MessageConstants;
import com.shivar.common.response.ApiResponse;
import com.shivar.common.response.ResponseBuilder;
import com.shivar.users.dto.RegisterUserRequest;
import com.shivar.users.dto.UpdateUserRequest;
import com.shivar.users.dto.UserResponse;
import com.shivar.users.enums.UserStatus;
import com.shivar.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "User Management",
        description = "APIs for managing users in SHIVAR"
)
@RestController
@RequestMapping(ApiConstants.API_V1 + "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Register User",
            description = "Creates a new user in the SHIVAR platform."
    )
    @PostMapping("/register")
    public ApiResponse<UserResponse> registerUser(
            @Valid @RequestBody RegisterUserRequest request) {

        UserResponse response = userService.registerUser(request);

        return ResponseBuilder.success(
                MessageConstants.USER_REGISTERED_SUCCESS,
                response
        );
    }

    @Operation(
            summary = "Get User By ID",
            description = "Returns user details using the user ID."
    )
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(
            @PathVariable Long id) {

        UserResponse userResponse = userService.getUserById(id);

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        MessageConstants.USER_FETCHED_SUCCESS,
                        userResponse
                )
        );
    }

    @Operation(
            summary = "Get All Users",
            description = "Returns the list of all registered users."
    )
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {

        List<UserResponse> users = userService.getAllUsers();

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        MessageConstants.USER_FETCHED_SUCCESS,
                        users
                )
        );
    }

    @Operation(
            summary = "Update User",
            description = "Updates an existing user's information."
    )
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request) {

        UserResponse response = userService.updateUser(id, request);

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        MessageConstants.USER_UPDATED_SUCCESS,
                        response
                )
        );
    }

    @Operation(
            summary = "Deactivate User",
            description = "Soft deletes a user by changing the status to INACTIVE."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deactivateUser(
            @PathVariable Long id) {

        userService.deactivateUser(id);

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        MessageConstants.USER_DEACTIVATED_SUCCESS,
                        null
                )
        );
    }

    @Operation(
            summary = "Search Users",
            description = "Search users by full name, email, or mobile number."
    )
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<UserResponse>>> searchUsers(
            @RequestParam String keyword) {

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        MessageConstants.USER_FETCHED_SUCCESS,
                        userService.searchUsers(keyword)
                )
        );
    }

    @Operation(
            summary = "Get Paginated Users",
            description = "Returns users with pagination and sorting support."
    )
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<UserResponse>>> getUsers(
            Pageable pageable) {

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        MessageConstants.USER_FETCHED_SUCCESS,
                        userService.getUsers(pageable)
                )
        );
    }

    @Operation(
            summary = "Get Users By Status",
            description = "Returns users filtered by their status."
    )
    @GetMapping("/status")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getUsersByStatus(
            @RequestParam UserStatus status) {

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        MessageConstants.USER_FETCHED_SUCCESS,
                        userService.getUsersByStatus(status)
                )
        );
    }

    @Operation(
            summary = "Activate User",
            description = "Changes the user status to ACTIVE."
    )
    @PatchMapping("/{id}/activate")
    public ResponseEntity<ApiResponse<UserResponse>> activateUser(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        MessageConstants.USER_ACTIVATED_SUCCESS,
                        userService.activateUser(id)
                )
        );
    }

    @Operation(
            summary = "Block User",
            description = "Changes the user status to BLOCKED."
    )
    @PatchMapping("/{id}/block")
    public ResponseEntity<ApiResponse<UserResponse>> blockUser(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        MessageConstants.USER_BLOCKED_SUCCESS,
                        userService.blockUser(id)
                )
        );
    }

    @Operation(
            summary = "Get Active Users",
            description = "Returns all users whose status is ACTIVE."
    )
    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getActiveUsers() {

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        MessageConstants.USER_FETCHED_SUCCESS,
                        userService.getActiveUsers()
                )
        );
    }
}