package ru.zagorovskiy.kinobase.domain.entiti;

import lombok.Data;

@Data
public class Comment {
    private Long id;
    private Long contentId;
    private Long profileId;
    private String comment;
}
