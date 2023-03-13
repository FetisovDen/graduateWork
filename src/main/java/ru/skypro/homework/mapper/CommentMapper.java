package ru.skypro.homework.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.entity.Comment;

import java.sql.Timestamp;

@Mapper(componentModel = "spring", uses = {CommentDtoToEntity.class})
public interface CommentMapper {
    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", expression = "java(comment.getUser().getId())")
    @Mapping(target = "createdAt", source = "createdAt", dateFormat = "yyyy-MM-dd HH:mm")
    CommentDto commentToDTO(Comment comment);

    @InheritInverseConfiguration
    @Mapping(target = "createdAt", source = "createdAt")
    Comment commentDtoToEntity(CommentDto commentDto);
}


