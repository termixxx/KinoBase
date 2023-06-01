package ru.zagorovskiy.kinobase.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.zagorovskiy.kinobase.domain.entiti.PersonContent;
import ru.zagorovskiy.kinobase.service.PersonContentService;
import ru.zagorovskiy.kinobase.web.dto.entiti.PersonContentDto;
import ru.zagorovskiy.kinobase.web.dto.mappers.PersonContentMapper;
import ru.zagorovskiy.kinobase.web.dto.validation.OnCreate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person-content")
@RequiredArgsConstructor
@Validated
@Tag(name = "Person Content controller", description = "Person Content API")
public class PersonContentController {

    private final PersonContentService personContentService;
    private final PersonContentMapper personContentMapper;

    @GetMapping("/{contentId}")
    public List<PersonContentDto> getAllPersonContentsByContentId(@PathVariable Long contentId) {
        List<PersonContent> personContents = personContentService.getAllByContentId(contentId);
        return personContentMapper.toDto(personContents);
    }

    @PostMapping("/add")
    public PersonContentDto addPersonContent(@Validated(OnCreate.class) @RequestBody PersonContentDto personContentDto) {
        PersonContent personContent = personContentMapper.toEntity(personContentDto);
        PersonContent createdPersonContent = personContentService.create(personContent);
        return personContentMapper.toDto(createdPersonContent);
    }

    @DeleteMapping
    public void deletePersonContent(@RequestBody PersonContentDto personContentDto) {
        personContentService.delete(personContentDto.getContentId(), personContentDto.getPersonId());
    }
}
