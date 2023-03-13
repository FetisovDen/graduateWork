package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.ImageService;

@CrossOrigin(value = "http://localhost:3000")
@PreAuthorize("hasRole('USER')")
@RestController
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PatchMapping(value = "/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateAdsImage(@PathVariable int id, @RequestPart("image") MultipartFile image) {
        byte[] img = imageService.updateImage(id, image);
        return ResponseEntity.ok()
                .contentLength(img.length)
                .contentType(MediaType.IMAGE_JPEG)
                .body(img);
    }
}
