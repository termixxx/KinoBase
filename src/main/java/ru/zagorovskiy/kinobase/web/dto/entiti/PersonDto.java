package ru.zagorovskiy.kinobase.web.dto.entiti;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.zagorovskiy.kinobase.domain.enums.Position;
import ru.zagorovskiy.kinobase.web.dto.validation.OnCreate;
import ru.zagorovskiy.kinobase.web.dto.validation.OnUpdate;

@Data
public class PersonDto {
    private Long id;

    @NotNull(message = "firstName cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private String firstName;

    @NotNull(message = "lastName cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private String lastName;

    @NotNull(message = "position cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private Position position;
}
