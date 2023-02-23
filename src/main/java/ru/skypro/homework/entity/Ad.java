package ru.skypro.homework.entity;

import lombok.Data;

@Data
public class Ad {
    private Integer pk;
    private String title;
    private Integer price;
    private String image;
    private String description;
}
