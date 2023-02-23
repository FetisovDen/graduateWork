package ru.skypro.homework.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String phone;
    private String lastName;
    private String firstName;
    private String email;
    private String city;
    private String regDate;
}
