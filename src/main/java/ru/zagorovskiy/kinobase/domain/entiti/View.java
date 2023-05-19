package ru.zagorovskiy.kinobase.domain.entiti;

import lombok.Data;
import ru.zagorovskiy.kinobase.domain.enums.Condition;

import java.time.LocalDate;

@Data
public class View {
    private Long contentId;
    private Long profileId;
    private boolean favorite;
    private Condition condition;
    private LocalDate addedAt;
}
