package com.shivar.users.controller;

import com.shivar.common.constants.ApiConstants;
import com.shivar.common.constants.MessageConstants;
import com.shivar.common.response.ApiResponse;
import com.shivar.common.response.ResponseBuilder;
import com.shivar.users.dto.RegisterUserRequest;
import com.shivar.users.dto.UserResponse;
import com.shivar.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.API_V1 + "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ApiResponse<UserResponse> registerUser(
            @Valid @RequestBody RegisterUserRequest request) {

        UserResponse response = userService.registerUser(request);

        return ResponseBuilder.success(
                MessageConstants.USER_REGISTERED_SUCCESS,
                response
        );
    }

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
}