package ru.zagorovskiy.kinobase.web.dto.entiti;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.zagorovskiy.kinobase.domain.enums.Position;
import ru.zagorovskiy.kinobase.web.dto.validation.OnCreate;
import ru.zagorovskiy.kinobase.web.dto.validation.OnUpdate;

@Data
@Schema(description = "Person DTO")
public class PersonDto {
    private Long id;

    @Schema(example = "Киллиан")
    @NotNull(message = "firstName cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private String firstName;

    @Schema(example = "Мёрфи")
    @NotNull(message = "lastName cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private String lastName;

    @Schema(example = "ACTOR")
    @NotNull(message = "position cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private Position position;
}
