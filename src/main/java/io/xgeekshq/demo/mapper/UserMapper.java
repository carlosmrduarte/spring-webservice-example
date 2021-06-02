package io.xgeekshq.demo.mapper;

import io.xgeekshq.demo.domain.User;
import io.xgeekshq.demo.dto.UserDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    List<UserDto> toDto(List<User> user);

    User toEntity(UserDto dto);

    List<User> toEntity(List<UserDto> dto);
}
