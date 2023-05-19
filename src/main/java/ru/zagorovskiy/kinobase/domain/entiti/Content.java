package ru.zagorovskiy.kinobase.domain.entiti;

import lombok.Data;
import ru.zagorovskiy.kinobase.domain.enums.Country;
import ru.zagorovskiy.kinobase.domain.enums.Genre;
import ru.zagorovskiy.kinobase.domain.enums.TypeOfContent;

import java.time.LocalDate;
import java.util.List;

@Data
public class Content {
    private Long id;
    private List<Genre> genreList;
    private List<Country> countryList;
    private String title;
    private String description;
    private TypeOfContent type;
    private LocalDate releaseYear;
    private String posterUrl;
}
