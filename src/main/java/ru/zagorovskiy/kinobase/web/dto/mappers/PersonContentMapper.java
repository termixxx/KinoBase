package ru.zagorovskiy.kinobase.web.dto.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.zagorovskiy.kinobase.domain.entiti.PersonContent;
import ru.zagorovskiy.kinobase.web.dto.entiti.PersonContentDto;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface PersonContentMapper {
    PersonContentDto toDto(PersonContent personContent);

    List<PersonContentDto> toDto(List<PersonContent> personContents);

    PersonContent toEntity(PersonContentDto dto);
}
