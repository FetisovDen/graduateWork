package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperCommentDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl {
    private final CommentRepository commentRepository;
    private final AdsRepository adsRepository;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository, AdsRepository adsRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.adsRepository = adsRepository;
        this.commentMapper = commentMapper;
    }

    public ResponseWrapperCommentDto getAllCommentsById(int adPk) {
        List<CommentDto> dtoList = new ArrayList<>();
        List<Comment> listComments = commentRepository.findAllById(adPk);

        for (int i = 0; i < listComments.size(); i++) {
            dtoList.add(i, commentMapper.commentToDTO(listComments.get(i)));
        }
        return new ResponseWrapperCommentDto(dtoList.size(), dtoList);
    }

    public CommentDto addComments(int adPk, CommentDto commentDto) {
        Comment comment = commentMapper.commentDtoToEntity(commentDto);
        comment.setAds(adsRepository.findAdsById(adPk));
        commentRepository.save(comment);
        return commentDto;
    }

    public CommentDto getComments(Ads ads, int id) {
        Comment comment = commentRepository.findCommentByAdsAndId(ads, id);
        return commentMapper.commentToDTO(comment);
    }

    public CommentDto deleteComments(Ads ads, int id) {
        Comment comment = commentRepository.deleteCommentByAdsAndId(ads, id);
        return commentMapper.commentToDTO(comment);
    }

    public CommentDto updateComments(Ads ads, int id, CommentDto commentDto) {
        Comment comment = commentRepository.findCommentByAdsAndId(ads, id);
        comment.setText(commentDto.getText());
        commentRepository.save(comment);
        return commentMapper.commentToDTO(comment);
    }
}
