package ru.zagorovskiy.kinobase.web.dto.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.zagorovskiy.kinobase.domain.entiti.Person;
import ru.zagorovskiy.kinobase.web.dto.entiti.PersonDto;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface PersonMapper {
    PersonDto toDto(Person person);

    List<PersonDto> toDto(List<Person> persons);

    Person toEntity(PersonDto dto);

}
