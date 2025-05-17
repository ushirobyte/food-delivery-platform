package com.ushirobyte.food.auth_service.mapper;

import com.ushirobyte.food.auth_service.dto.UserDto;
import com.ushirobyte.food.auth_service.model.User;

public class UserMapper {
    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole())
                .build();
    }
}
