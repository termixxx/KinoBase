package ru.zagorovskiy.kinobase.web.dto.entiti;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.zagorovskiy.kinobase.domain.enums.Condition;
import ru.zagorovskiy.kinobase.web.dto.validation.OnCreate;
import ru.zagorovskiy.kinobase.web.dto.validation.OnUpdate;

import java.time.LocalDate;

@Data
@Schema(description = "View DTO")
public class ViewDto {

    @Schema(example = "1")
    @NotNull(message = "contentId cannot be empty", groups = OnCreate.class)
    private Long contentId;

    @Schema(example = "1")
    @NotNull(message = "profileId cannot be empty", groups = OnCreate.class)
    private Long profileId;

    @Schema(example = "true")
    @NotNull(message = "favorite cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private boolean favorite;

    @Schema(example = "PLANNED")
    @NotNull(message = "condition cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private Condition condition;

    private LocalDate addedAt;
}
