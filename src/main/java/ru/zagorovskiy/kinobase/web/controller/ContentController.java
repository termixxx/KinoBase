package ru.zagorovskiy.kinobase.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.zagorovskiy.kinobase.domain.entiti.Content;
import ru.zagorovskiy.kinobase.service.ContentService;
import ru.zagorovskiy.kinobase.web.dto.entiti.ContentDto;
import ru.zagorovskiy.kinobase.web.dto.mappers.ContentMapper;
import ru.zagorovskiy.kinobase.web.dto.validation.OnCreate;

@RestController
@RequestMapping("/api/v1/content")
@RequiredArgsConstructor
@Validated
@Tag(name = "Content controller", description = "Content API")
public class ContentController {
    private final ContentService contentService;
    private final ContentMapper contentMapper;

    @GetMapping("/{id}")
    public ContentDto getById(@PathVariable Long id) {
        Content content = contentService.getById(id);
        return contentMapper.toDto(content);
    }

    @PostMapping("/add")
    public ContentDto createContent(@Validated(OnCreate.class) @RequestBody ContentDto contentDto) {
        Content content = contentMapper.toEntity(contentDto);
        contentService.create(content);
        contentDto.setId(content.getId());
        return contentDto;
    }
}
