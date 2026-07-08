package com.shivar.users.service.impl;

import com.shivar.common.constants.MessageConstants;
import com.shivar.exception.BadRequestException;
import com.shivar.users.dto.RegisterUserRequest;
import com.shivar.users.dto.UpdateUserRequest;
import com.shivar.users.dto.UserResponse;
import com.shivar.users.entity.User;
import com.shivar.users.enums.UserStatus;
import com.shivar.exception.EmailAlreadyExistsException;
import com.shivar.exception.MobileNumberAlreadyExistsException;
import com.shivar.exception.ResourceNotFoundException;
import com.shivar.users.mapper.UserMapper;
import com.shivar.users.repository.UserRepository;
import com.shivar.users.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
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


    @Override
    public UserResponse updateUser(Long id, UpdateUserRequest request) {

        // Fetch existing user
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MessageConstants.USER_NOT_FOUND));

        // Check duplicate email (excluding current user)
        if (userRepository.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new EmailAlreadyExistsException(
                    MessageConstants.EMAIL_ALREADY_EXISTS);
        }

        // Check duplicate mobile number (excluding current user)
        if (userRepository.existsByMobileNumberAndIdNot(request.getMobileNumber(), id)) {
            throw new MobileNumberAlreadyExistsException(
                    MessageConstants.MOBILE_NUMBER_ALREADY_EXISTS);
        }

        // Update editable fields
        userMapper.updateUserFromRequest(request, user);

        // Save updated user
        User updatedUser = userRepository.save(user);

        // Convert Entity -> DTO
        return userMapper.toResponse(updatedUser);
    }
 // Delete user
    @Override
    public void deactivateUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MessageConstants.USER_NOT_FOUND));

        user.setStatus(UserStatus.INACTIVE);

        userRepository.save(user);
    }

    //
    @Override
    public List<UserResponse> searchUsers(String keyword) {

        List<User> users;

        if (keyword.matches("\\d+")) {
            users = userRepository.findByMobileNumberContaining(keyword);
        } else if (keyword.contains("@")) {
            users = userRepository.findByEmailContainingIgnoreCase(keyword);
        } else {
            users = userRepository.findByFullNameContainingIgnoreCase(keyword);
        }

        return userMapper.toResponseList(users);
    }

    @Override
    public Page<UserResponse> getUsers(Pageable pageable) {

        return userRepository.findAll(pageable)
                .map(userMapper::toResponse);
    }


    @Override
    public List<UserResponse> getUsersByStatus(UserStatus status) {

        List<User> users = userRepository.findByStatus(status);

        return userMapper.toResponseList(users);
    }

    @Override
    public UserResponse activateUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(MessageConstants.USER_NOT_FOUND));

        if (user.getStatus() == UserStatus.ACTIVE) {
            throw new BadRequestException("User is already active.");
        }

        user.setStatus(UserStatus.ACTIVE);

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse blockUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(MessageConstants.USER_NOT_FOUND));

        if (user.getStatus() == UserStatus.BLOCKED) {
            throw new BadRequestException("User is already blocked.");
        }

        user.setStatus(UserStatus.BLOCKED);

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

    @Override
    public List<UserResponse> getActiveUsers() {

        List<User> users = userRepository.findByStatus(UserStatus.ACTIVE);

        return userMapper.toResponseList(users);
    }

}