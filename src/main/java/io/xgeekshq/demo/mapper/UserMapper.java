package io.xgeekshq.demo.mapper;

import org.mapstruct.Mapper;

import io.xgeekshq.demo.domain.User;
import io.xgeekshq.demo.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto dto);
}