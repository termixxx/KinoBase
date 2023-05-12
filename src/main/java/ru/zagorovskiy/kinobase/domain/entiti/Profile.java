package ru.zagorovskiy.kinobase.entity;

import lombok.Data;

@Data
public class Profile {
    private Long id;
    private String login;
    private String password;
    private String email;
    private String nickname;
}
