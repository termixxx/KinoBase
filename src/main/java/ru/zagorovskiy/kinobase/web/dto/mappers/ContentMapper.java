package ru.zagorovskiy.kinobase.web.dto.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.zagorovskiy.kinobase.domain.entiti.Content;
import ru.zagorovskiy.kinobase.web.dto.entiti.ContentDto;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ContentMapper {
    ContentDto toDto(Content content);

    List<ContentDto> toDto(List<Content> contents);

    Content toEntity(ContentDto dto);
}
