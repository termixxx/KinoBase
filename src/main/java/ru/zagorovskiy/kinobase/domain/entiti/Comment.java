package ru.zagorovskiy.kinobase.domain.entiti;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Comment {
    private Long id;
    private Long contentId;
    private Long profileId;
    private String comment;
    private LocalDate addedAt;
}
