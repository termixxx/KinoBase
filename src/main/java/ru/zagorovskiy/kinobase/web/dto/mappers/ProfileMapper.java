package ru.zagorovskiy.kinobase.web.dto.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.zagorovskiy.kinobase.domain.entiti.Profile;
import ru.zagorovskiy.kinobase.web.dto.entiti.ProfileDto;

@Mapper(componentModel = "spring")
@Component
public interface ProfileMapper {
    ProfileDto toDto(Profile profile);

    Profile toEntity(ProfileDto dto);
}
