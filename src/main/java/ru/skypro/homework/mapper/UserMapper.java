package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDTO(User user);

//    User toEntity(UserDto userDto);

}
