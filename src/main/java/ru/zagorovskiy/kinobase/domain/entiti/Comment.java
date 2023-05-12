package ru.zagorovskiy.kinobase.entity;

import lombok.Data;

@Data
public class Comment {
    private Long contentId;
    private Long profileId;
    private String comment;
}
