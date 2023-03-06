package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.impl.AdsServiceImpl;
import ru.skypro.homework.service.impl.CommentServiceImpl;
import ru.skypro.homework.service.impl.ImageServiceImpl;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {
    private final AdsServiceImpl adsService;

    public AdsController(AdsServiceImpl adsService) {
        this.adsService = adsService;
    }


    @GetMapping()
    public ResponseEntity<ResponseWrapperAdsDto> getAds() {
        return ResponseEntity.ok(adsService.getAllAds());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDto> addAds(@RequestPart CreateAdsDto createAdsDto, @RequestPart MultipartFile multipartFile) {
        return ResponseEntity.ok(adsService.addAds(createAdsDto, multipartFile));
    }

    @GetMapping( "/{ad_pk}/comments")
    public ResponseEntity <ResponseWrapperCommentDto> getComments(@PathVariable("ad_pk") int adPk) {
        return ResponseEntity.ok(adsService.getAllCommentsById(adPk));
    }

    @PostMapping("/{ad_pk}/comments")
    public ResponseEntity<CommentDto> addComments(@PathVariable("ad_pk") int adPk, @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(adsService.addComments(adPk, commentDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FullAdsDto> getFullAds(@PathVariable int id) {
        return ResponseEntity.ok(adsService.getFullAds(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FullAdsDto> deleteAds(@PathVariable int id) {
        return ResponseEntity.ok(adsService.deleteAds(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdsDto> updateAds(@PathVariable int id, @RequestBody CreateAdsDto createAdsDto) {
        return ResponseEntity.ok(adsService.updateAds(id,createAdsDto));
    }

    @GetMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<CommentDto> getComments(@PathVariable("ad_pk") int adPk, @PathVariable int id) {
        return ResponseEntity.ok(adsService.getComments(adPk,id));
    }

    @DeleteMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<CommentDto>deleteComments(@PathVariable("ad_pk") int adPk, @PathVariable int id) {
//        add BadRequest
        return ResponseEntity.ok(adsService.deleteComments(adPk,id));
    }

    @PatchMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<CommentDto> updateComments(@PathVariable("ad_pk") int adPk, @PathVariable int id, @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(adsService.updateComments(adPk,id,commentDto));
    }

    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperAdsDto> getAdsMe() {
        return ResponseEntity.ok(adsService.getAdsMe());
    }
}
