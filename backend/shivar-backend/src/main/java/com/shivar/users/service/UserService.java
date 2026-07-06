package com.shivar.users.service;

import com.shivar.users.dto.RegisterUserRequest;
import com.shivar.users.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse registerUser(RegisterUserRequest request);

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();

}