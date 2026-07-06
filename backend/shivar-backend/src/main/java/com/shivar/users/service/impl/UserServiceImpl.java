package com.shivar.users.service.impl;

import com.shivar.common.constants.MessageConstants;
import com.shivar.users.dto.RegisterUserRequest;
import com.shivar.users.dto.UserResponse;
import com.shivar.users.entity.User;
import com.shivar.users.enums.UserStatus;
import com.shivar.exception.EmailAlreadyExistsException;
import com.shivar.exception.MobileNumberAlreadyExistsException;
import com.shivar.exception.ResourceNotFoundException;
import com.shivar.users.mapper.UserMapper;
import com.shivar.users.repository.UserRepository;
import com.shivar.users.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse registerUser(RegisterUserRequest request) {

        // Check duplicate mobile number
        if (userRepository.existsByMobileNumber(request.getMobileNumber())) {
            throw new MobileNumberAlreadyExistsException(
                    MessageConstants.MOBILE_NUMBER_ALREADY_EXISTS);
        }

        // Check duplicate email
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(
                    MessageConstants.EMAIL_ALREADY_EXISTS);
        }

        // DTO -> Entity
        User user = userMapper.toEntity(request);

        // Default values
        user.setStatus(UserStatus.ACTIVE);
        user.setMobileVerified(false);
        user.setEmailVerified(false);

        // Save User
        User savedUser = userRepository.save(user);

        // Entity -> Response DTO
        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MessageConstants.USER_NOT_FOUND));

        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {

        List<User> users = userRepository.findAll();

        return userMapper.toResponseList(users);
    }


}