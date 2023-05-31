package ru.zagorovskiy.kinobase.web.dto.entiti;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.zagorovskiy.kinobase.web.dto.validation.OnCreate;
import ru.zagorovskiy.kinobase.web.dto.validation.OnUpdate;

import java.time.LocalDate;

@Data
public class CommentDto {
    private Long id;

    @Schema(example = "1")
    @NotNull(message = "contentId cannot be empty", groups = OnCreate.class)
    private Long contentId;

    @Schema(example = "1")
    @NotNull(message = "profileId cannot be empty", groups = OnCreate.class)
    private Long profileId;

    @Schema(example = "Good content, you should see it")
    @NotNull(message = "comment cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 500, message = "comment is too long (max 500 symbols)")
    private String comment;

    private LocalDate addedAt;
}
