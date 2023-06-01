package ru.zagorovskiy.kinobase.web.dto.entiti;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Content DTO")
public class ContentDto {
    private Long id;

    @Schema(example = "[\"DRAMA\", \"ADVENTURE\", \"ACTION\"]")
    @NotNull(message = "genres cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private List<Genre> genreList;

    @Schema(example = "[\"USA\", \"UNITED_KINGDOM\", \"CANADA\"]")
    @NotNull(message = "countries cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private List<Country> countryList;

    @Schema(example = "Интерстеллар")
    @NotNull(message = "title cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private String title;

    @Schema(example = "Когда засуха, пыльные бури и вымирание растений приводят человечество к продовольственному кризису, коллектив исследователей и учёных отправляется сквозь червоточину (которая предположительно соединяет области пространства-времени через большое расстояние) в путешествие, чтобы превзойти прежние ограничения для космических путешествий человека и найти планету с подходящими для человечества условиями.")
    private String description;

    @Schema(example = "MOVIE")
    @NotNull(message = "type cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private TypeOfContent type;

    @Schema(example = "2014-01-01")
    private LocalDate releaseDate;

    @Schema(example = "https://i.pinimg.com/736x/92/27/e8/9227e8fab39e7d58df3278aa7244ee45--interstellar-drawing-art.jpg")
    private String posterUrl;
}
