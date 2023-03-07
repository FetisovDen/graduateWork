package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Avatar;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdsRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdsService {
    private final AdsRepository adsRepository;
    private final CommentService commentService;
    private final AdsMapper adsMapper;
    private final ImageService imageService;

    public AdsService(AdsRepository adsRepository, CommentService commentService, AdsMapper adsMapper, ImageService imageService) {
        this.adsRepository = adsRepository;
        this.commentService = commentService;
        this.adsMapper = adsMapper;
        this.imageService = imageService;
    }


    public ResponseWrapperAdsDto getAllAds() {
        List<Ads> listAds = adsRepository.findAll();
        List<AdsDto> listAdsDto = new ArrayList<>();
        for (Ads listAd : listAds) {
            listAdsDto.add(adsMapper.adsToDTO(listAd));
        }
        return new ResponseWrapperAdsDto(listAdsDto.size(), listAdsDto);
    }

    public AdsDto addAds(CreateAdsDto createAdsDto, MultipartFile multipartFile) {
        Ads ads = adsMapper.adsToEntity(createAdsDto);
        ads.setUser(new User(1,
                "1",
                "1",
                "1",
                "1",
                "1"
                , Timestamp.valueOf(LocalDateTime.now()),
                "user@gmail.com",
                "password"
                , new Avatar(),
                Role.USER));
        ads = adsRepository.save(ads);
        ads.setImage(imageService.addImage(ads, multipartFile));
        ads = adsRepository.save(ads);
        return adsMapper.adsToDTO(ads);
    }

    public ResponseWrapperCommentDto getAllCommentsByAdsId(int adPk) {
        Ads ads = adsRepository.findById(adPk).orElseThrow(AdsNotFoundException::new);
        return commentService.getAllCommentsByAds(ads);
    }

    public CommentDto addComments(int adPk, CommentDto commentDto) {
        if (adPk < 0 || commentDto == null) {
            throw new IllegalArgumentException();
        }
        Ads ads = adsRepository.findById(adPk).orElseThrow(AdsNotFoundException::new);
        return commentService.addComments(ads, commentDto);
    }


    public FullAdsDto getFullAds(int id) {
        if (id < 0) {
            throw new IllegalArgumentException();
        }
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);

        return adsMapper.adsToFullAdsDTO(ads);
    }

    public FullAdsDto deleteAds(int id) {
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        adsRepository.deleteById(id);
        return adsMapper.adsToFullAdsDTO(ads);
    }

    public AdsDto updateAds(int id, CreateAdsDto createAdsDto) {
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        ads.setDescription(createAdsDto.getDescription());
        ads.setPrice(createAdsDto.getPrice());
        ads.setTitle(createAdsDto.getTitle());
        adsRepository.save(ads);
        return adsMapper.adsToDTO(ads);
    }

    public CommentDto getCommentOfAds(int adPk, int id) {
        Ads ads = adsRepository.findById(adPk).orElseThrow(AdsNotFoundException::new);
        return commentService.getCommentOfAds(ads, id);
    }

    public CommentDto deleteCommentOfAds(int adPk, int id) {
        Ads ads = adsRepository.findById(adPk).orElseThrow(AdsNotFoundException::new);
        return commentService.deleteCommentOfAds(ads, id);
    }

    public CommentDto updateComments(int adPk, int id, CommentDto commentDto) {
        Ads ads = adsRepository.findById(adPk).orElseThrow(AdsNotFoundException::new);
        return commentService.updateComments(ads, id, commentDto);
    }

    public ResponseWrapperAdsDto getAdsMe() {
        List<Ads> listAds = adsRepository.findAll(); // findByUser(user)
        List<AdsDto> listAdsDto = new ArrayList<>();
        for (Ads listAd : listAds) {
            listAdsDto.add(adsMapper.adsToDTO(listAd));
        }
        return new ResponseWrapperAdsDto(listAdsDto.size(), listAdsDto);
    }
}
