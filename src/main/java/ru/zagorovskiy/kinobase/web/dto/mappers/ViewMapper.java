package ru.zagorovskiy.kinobase.web.dto.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.zagorovskiy.kinobase.domain.entiti.View;
import ru.zagorovskiy.kinobase.web.dto.entiti.ViewDto;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ViewMapper {
    ViewDto toDto(View view);

    List<ViewDto> toDto(List<View> views);

    View toEntity(ViewDto dto);
}
