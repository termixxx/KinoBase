package ru.zagorovskiy.kinobase.web.dto.entiti;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.zagorovskiy.kinobase.web.dto.validation.OnCreate;

@Data
public class PersonContentDto {
    
    @Schema(example = "1")
    @NotNull(message = "personId cannot be empty", groups = OnCreate.class)
    private Long personId;

    @Schema(example = "1")
    @NotNull(message = "contentId cannot be empty", groups = OnCreate.class)
    private Long contentId;
}
