package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.Avatar;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final AvatarServiceImpl avatarServiceImpl;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, AvatarServiceImpl avatarServiceImpl, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.avatarServiceImpl = avatarServiceImpl;
        this.userMapper = userMapper;
    }

    public NewPasswordDto setPassword(NewPasswordDto newPasswordDto) {
        User user = new User(1, "1", "1", "1", "1", "1", Timestamp.valueOf(LocalDateTime.now()), "user@gmail.com", "password", new Avatar(), Role.USER);
        if (user.getPassword().equals(newPasswordDto.getCurrentPassword())) {
            user.setPassword(newPasswordDto.getNewPassword());
            userRepository.save(user);
        }
        return newPasswordDto;
    }

    private User getUser(int userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }
    public UserDto getUserDto(int userId) {
        return userMapper.toDTO(getUser(userId));
    }
    public UserDto updateUser(UserDto userDto) {
        User user = getUser(userMapper.toEntity(userDto).getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public void updateUserImage(int id, MultipartFile avatar) {
        User user = getUser(id);
        avatarServiceImpl.updateAvatar(user, avatar);
    }
}
