package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.Avatar;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.PasswordMismatchException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AvatarService;

import java.time.LocalDateTime;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AvatarService avatarService;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, AvatarService avatarService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.avatarService = avatarService;
        this.userMapper = userMapper;
    }

    public NewPasswordDto setPassword(int id, NewPasswordDto newPasswordDto) throws PasswordMismatchException {
        User user = getUser(id);
        if (user.getPassword().equals(newPasswordDto.getCurrentPassword())) {
            user.setPassword(newPasswordDto.getNewPassword());
            userRepository.save(user);
        } else {
            throw new PasswordMismatchException();
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
        User user1 = new User(1,
                "1",
                "1",
                "1",
                "1",
                "1",
                LocalDateTime.now(),
                "user@gmail.com",
                "password"
                , new Avatar(1,null),
                Role.USER);
        user1.setFirstName(userDto.getFirstName());
        user1.setLastName(userDto.getLastName());
        user1.setPhone(userDto.getPhone());
        System.out.println("1");
        userRepository.save(user1);
        System.out.println(user1);
        return userMapper.toDTO(user1);
    }

    public void updateUserImage(int id, MultipartFile file) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setAvatar(avatarService.updateAvatar(user, file));
        userRepository.save(user);
    }
}
