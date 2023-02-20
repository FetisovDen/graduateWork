package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/image")
public class ImageController {
    @PatchMapping(value = "/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateAdsImage(@PathVariable int id, @RequestPart("image") MultipartFile image) {
        return null;
    }

}
