package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Avatar;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.AvatarRepository;

@Service
public class AvatarServiceImpl {
    private final AvatarRepository avatarRepository;
    private final String avatarDir;

    public AvatarServiceImpl(@Value("${path.to.files.folder}avatar") String avatarDir, AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
        this.avatarDir = avatarDir;
    }

    public void updateAvatar(User user, MultipartFile avatar) {
        //take old and update on new
    }
}
