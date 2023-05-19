package ru.zagorovskiy.kinobase.web.dto.entiti;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.zagorovskiy.kinobase.domain.enums.Country;
import ru.zagorovskiy.kinobase.domain.enums.Genre;
import ru.zagorovskiy.kinobase.domain.enums.TypeOfContent;
import ru.zagorovskiy.kinobase.web.dto.validation.OnCreate;
import ru.zagorovskiy.kinobase.web.dto.validation.OnUpdate;

import java.time.LocalDate;
import java.util.List;

@Data
public class ContentDto {
    private Long id;

    @NotNull(message = "genres cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private List<Genre> genreList;

    @NotNull(message = "countries cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private List<Country> countryList;

    @NotNull(message = "title cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private String title;

    private String description;

    @NotNull(message = "type cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private TypeOfContent type;

    private LocalDate releaseYear;

    private String posterUrl;
}
