package ru.zagorovskiy.kinobase.web.dto.entiti;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.zagorovskiy.kinobase.web.dto.validation.OnCreate;

@Data
public class PersonContentDto {
    @NotNull(message = "personId cannot be empty", groups = OnCreate.class)
    private Long personId;

    @NotNull(message = "contentId cannot be empty", groups = OnCreate.class)
    private Long contentId;
}
