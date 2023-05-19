package ru.zagorovskiy.kinobase.web.dto.entiti;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.zagorovskiy.kinobase.web.dto.validation.OnCreate;
import ru.zagorovskiy.kinobase.web.dto.validation.OnUpdate;

@Data
public class RatingDto {
    @NotNull(message = "contentId cannot be empty", groups = OnCreate.class)
    private Long contentId;

    @NotNull(message = "profileId cannot be empty", groups = OnCreate.class)
    private Long profileId;

    @Min(value = 0, message = "rating must be > 0")
    @Max(value = 10, message = "rating must be < 10")
    @NotNull(message = "rating value cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private Integer value;
}
