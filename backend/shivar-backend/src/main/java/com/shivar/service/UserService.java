package com.shivar.service;

import com.shivar.dto.request.RegisterUserRequest;
import com.shivar.dto.response.UserResponse;

public interface UserService {

    UserResponse registerUser(RegisterUserRequest request);

}