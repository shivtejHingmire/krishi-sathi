package com.shivar.users.service;

import com.shivar.users.dto.RegisterUserRequest;
import com.shivar.users.dto.UpdateUserRequest;
import com.shivar.users.dto.UserResponse;
import com.shivar.users.enums.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserService {

    UserResponse registerUser(RegisterUserRequest request);

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(Long id, UpdateUserRequest request);

    void deactivateUser(Long id);

    List<UserResponse> searchUsers(String keyword);

    Page<UserResponse> getUsers(Pageable pageable);

    List<UserResponse> getUsersByStatus(UserStatus status);

    UserResponse activateUser(Long id);

    UserResponse blockUser(Long id);

    List<UserResponse> getActiveUsers();

}