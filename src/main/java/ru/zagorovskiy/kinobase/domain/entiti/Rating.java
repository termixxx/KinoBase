package ru.zagorovskiy.kinobase.entity;

import lombok.Data;

@Data
public class Rating {
    private Long contentId;
    private Long profileId;
    private Integer value;
}
