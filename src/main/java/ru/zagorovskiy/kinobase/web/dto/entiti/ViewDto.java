package ru.zagorovskiy.kinobase.web.dto.entiti;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.zagorovskiy.kinobase.domain.enums.Condition;
import ru.zagorovskiy.kinobase.web.dto.validation.OnCreate;
import ru.zagorovskiy.kinobase.web.dto.validation.OnUpdate;

import java.time.LocalDate;

@Data
public class ViewDto {
    @NotNull(message = "contentId cannot be empty", groups = OnCreate.class)
    private Long contentId;

    @NotNull(message = "profileId cannot be empty", groups = OnCreate.class)
    private Long profileId;

    @NotNull(message = "favorite cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private boolean favorite;

    @NotNull(message = "condition cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private Condition condition;

    private LocalDate addedAt;
}
