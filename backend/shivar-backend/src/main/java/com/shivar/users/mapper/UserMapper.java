package com.shivar.users.mapper;

import com.shivar.users.dto.RegisterUserRequest;
import com.shivar.users.dto.UpdateUserRequest;
import com.shivar.users.dto.UserResponse;
import com.shivar.users.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Maps User related DTOs and Entity.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "mobileVerified", ignore = true)
    @Mapping(target = "emailVerified", ignore = true)
    @Mapping(target = "lastLoginAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toEntity(RegisterUserRequest request);

    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(List<User> users);

    void updateUserFromRequest(UpdateUserRequest request,
                               @MappingTarget User user);
}