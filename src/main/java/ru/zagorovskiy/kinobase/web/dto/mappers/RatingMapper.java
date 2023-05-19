package ru.zagorovskiy.kinobase.web.dto.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.zagorovskiy.kinobase.domain.entiti.Rating;
import ru.zagorovskiy.kinobase.web.dto.entiti.RatingDto;

@Mapper(componentModel = "spring")
@Component
public interface RatingMapper {
    RatingDto toDto(Rating rating);

    Rating toEntity(RatingDto dto);
}
