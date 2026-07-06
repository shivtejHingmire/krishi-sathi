package com.shivar.mapper;

import com.shivar.dto.request.RegisterUserRequest;
import com.shivar.dto.response.UserResponse;
import com.shivar.entity.User;
import org.mapstruct.Mapper;

/**
 * Maps User related DTOs and Entity.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterUserRequest request);

    UserResponse toResponse(User user);

}