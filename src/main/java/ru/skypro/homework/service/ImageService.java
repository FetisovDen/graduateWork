package ru.skypro.homework.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.ImageRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class ImageService {
    private final String imageDir;
    private final ImageRepository imageRepository;
    private final AdsRepository adsRepository;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ImageService(@Value("${path.to.images.folder}.") String imageDir, ImageRepository imageRepository,
                        AdsRepository adsRepository) {
        this.imageDir = imageDir;
        this.imageRepository = imageRepository;
        this.adsRepository = adsRepository;
    }

    public Image addImage(Ads ads, MultipartFile multipartFile) {
        try {
            byte[] img = multipartFile.getBytes();
            Path path = Path.of(imageDir, ads.getDescription()+ LocalDateTime.now().format(format) + ".jpg");
            Files.createDirectories(path.getParent());
            Files.write(path, img);
            Image image = new Image();
            image.setPathImage(path.toString());
            image = imageRepository.save(image);
            return image;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] updateImage(int id, MultipartFile image) {
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        Image oldImage = ads.getImage();
        imageRepository.delete(oldImage);
        addImage(ads, image);
        try {
            return image.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
