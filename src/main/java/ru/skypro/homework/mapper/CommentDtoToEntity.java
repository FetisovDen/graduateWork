package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
@Component
public class CommentDtoToEntity {
    Timestamp mapStringToTimestamp(String string) {
        return Timestamp.valueOf(string);
    }
}
