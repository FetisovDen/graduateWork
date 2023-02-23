package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Avatar;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdsRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdsServiceImpl {
    private final AdsRepository adsRepository;
    private final CommentServiceImpl commentServiceImpl;
    private final AdsMapper adsMapper;
    private final ImageServiceImpl imageServiceImpl;

    public AdsServiceImpl(AdsRepository adsRepository, CommentServiceImpl commentServiceImpl, AdsMapper adsMapper, ImageServiceImpl imageServiceImpl) {
        this.adsRepository = adsRepository;
        this.commentServiceImpl = commentServiceImpl;
        this.adsMapper = adsMapper;
        this.imageServiceImpl = imageServiceImpl;
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
        ads.setImage(imageServiceImpl.addImage(ads, multipartFile));
        ads = adsRepository.save(ads);
        return adsMapper.adsToDTO(ads);
    }

    public ResponseWrapperCommentDto getAllCommentsById(int adPk) {
        return commentServiceImpl.getAllCommentsById(adPk);
    }

    public CommentDto addComments(int adPk, CommentDto commentDto) {
        return commentServiceImpl.addComments(adPk, commentDto);
    }

    public FullAdsDto getFullAds(int id) {
        Ads ads = adsRepository.findAdsById(id);
        return adsMapper.adsToFullAdsDTO(ads);
    }

    public FullAdsDto deleteAds(int id) {
        Ads ads = adsRepository.findAdsById(id);
        adsRepository.deleteById(id);
        return adsMapper.adsToFullAdsDTO(ads);
    }

    public AdsDto updateAds(int id, CreateAdsDto createAdsDto) {
        Ads ads = adsRepository.findAdsById(id);
        ads.setDescription(createAdsDto.getDescription());
        ads.setPrice(createAdsDto.getPrice());
        ads.setTitle(createAdsDto.getTitle());
        adsRepository.save(ads);
        return adsMapper.adsToDTO(ads);
    }

    public CommentDto getComments(int adPk, int id) {
        Ads ads = adsRepository.findAdsById(adPk);
        return commentServiceImpl.getComments(ads, id);
    }

    public CommentDto deleteComments(int adPk, int id) {
        Ads ads = adsRepository.findAdsById(adPk);
        return commentServiceImpl.deleteComments(ads, id);
    }

    public CommentDto updateComments(int adPk, int id, CommentDto commentDto) {
        Ads ads = adsRepository.findAdsById(adPk);
        return commentServiceImpl.updateComments(ads, id, commentDto);
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
