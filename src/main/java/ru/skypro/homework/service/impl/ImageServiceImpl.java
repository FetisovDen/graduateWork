package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.repository.ImageRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class ImageServiceImpl {
    private final String imageDir;
    private final ImageRepository imageRepository;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ImageServiceImpl(@Value("${path.to.files.folder}image") String imageDir, ImageRepository imageRepository) {
        this.imageDir = imageDir;
        this.imageRepository = imageRepository;
    }

    public Image addImage(Ads ads, MultipartFile multipartFile)  {
        try {
        byte[] img = multipartFile.getBytes();
        Path path = Path.of(imageDir, ads.getId() + "_" + LocalDateTime.now().format(format) + ".jpg");
        Files.write(path, img);
        Image image = new Image();
        image.setPathImage(path.toString());
        image = imageRepository.save(image);
        return image;
        }
        catch (IOException e) {
            throw new RuntimeException(e);}
    }

    public byte[] updateImage(int id, MultipartFile image) {
        try {
            return image.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
