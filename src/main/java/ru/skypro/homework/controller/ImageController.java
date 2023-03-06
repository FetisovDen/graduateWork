package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.impl.ImageServiceImpl;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/image")
public class ImageController {
    private final ImageServiceImpl imageServiceImpl;

    public ImageController(ImageServiceImpl imageServiceImpl) {
        this.imageServiceImpl = imageServiceImpl;
    }

    @PatchMapping(value = "/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateAdsImage(@PathVariable int id, @RequestPart("image") MultipartFile image) {
        byte[] img = imageServiceImpl.updateImage(id, image);
        return ResponseEntity.ok()
                .contentLength(img.length)
                .contentType(MediaType.IMAGE_JPEG)
                .body(img);
    }
}
