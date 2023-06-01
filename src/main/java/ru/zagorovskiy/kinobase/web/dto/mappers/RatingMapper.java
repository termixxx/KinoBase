package ru.zagorovskiy.kinobase.web.dto.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.zagorovskiy.kinobase.domain.entiti.Rating;
import ru.zagorovskiy.kinobase.web.dto.entiti.RatingDto;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface RatingMapper {
    RatingDto toDto(Rating rating);

    List<RatingDto> toDto(List<Rating> ratings);

    Rating toEntity(RatingDto dto);
}
