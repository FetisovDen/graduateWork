package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.impl.UserService;

@Slf4j
@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/{id}/set_password")
    public ResponseEntity<NewPasswordDto> setPassword(@PathVariable int id, @RequestBody NewPasswordDto newPasswordDto) {
        return ResponseEntity.ok(userService.setPassword(id, newPasswordDto));
    }

    @GetMapping( "/me/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUserDto(id));
    }

    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(userDto));
    }

    @PatchMapping(path = "/me/{id}/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<UserDto> updateUserImage(@PathVariable int id, @RequestPart("MultipartFile") MultipartFile avatar) {
        userService.updateUserImage(id, avatar);
        return ResponseEntity.ok().build();
    }


}
