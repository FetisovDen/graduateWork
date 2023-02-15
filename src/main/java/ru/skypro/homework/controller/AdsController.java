package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {

    @GetMapping()
    public ResponseEntity<ResponseWrapperAdsDto> getAds() {
        return null;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDto> addAds(@RequestBody CreateAdsDto adsDto, @RequestBody MultipartFile multipartFile) {
        return null;
    }

    @GetMapping( "/{ad_pk}/comments")
    public ResponseEntity <ResponseWrapperCommentDto> getComments(@PathVariable("ad_pk") int adPk) {
        return null;
    }

    @PostMapping("/{ad_pk}/comments")
    public ResponseEntity<CommentDto> addComments(@PathVariable("ad_pk") int adPk, @RequestBody CommentDto commentDto) {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FullAdsDto> getFullAds(@PathVariable int id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FullAdsDto> deleteAds(@PathVariable int id) {
        return  null;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdsDto> updateAds(@PathVariable int id, @RequestBody CreateAdsDto createAdsDto) {
        return null;
    }

    @GetMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<CommentDto> getComments(@PathVariable("ad_pk") int adPk, @PathVariable int id) {
        return null;
    }

    @DeleteMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<CommentDto>deleteComments(@PathVariable("ad_pk") int adPk, @PathVariable int id) {
        return null;
    }

    @PatchMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<CommentDto> updateComments(@PathVariable("ad_pk") int adPk, @PathVariable int id, @RequestBody CommentDto commentDto) {
        return null;
    }

    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperAdsDto> getAdsMe(@RequestParam int userName) {
        return null;
    }
}
