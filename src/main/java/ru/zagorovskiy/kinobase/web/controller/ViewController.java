package ru.zagorovskiy.kinobase.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.zagorovskiy.kinobase.domain.entiti.View;
import ru.zagorovskiy.kinobase.service.ViewService;
import ru.zagorovskiy.kinobase.web.dto.entiti.ViewDto;
import ru.zagorovskiy.kinobase.web.dto.mappers.ViewMapper;
import ru.zagorovskiy.kinobase.web.dto.validation.OnCreate;
import ru.zagorovskiy.kinobase.web.dto.validation.OnUpdate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/view")
@RequiredArgsConstructor
@Validated
@Tag(name = "View controller", description = "View API")
public class ViewController {
    private final ViewService viewService;
    private final ViewMapper viewMapper;

    @GetMapping("/{profileId}")
    public List<ViewDto> getAllViewsByProfileId(@PathVariable Long profileId) {
        List<View> views = viewService.getAllByProfileId(profileId);
        return viewMapper.toDto(views);
    }

    @PostMapping("/add")
    public ViewDto addView(@Validated(OnCreate.class) @RequestBody ViewDto viewDto) {
        View view = viewMapper.toEntity(viewDto);
        View createdView = viewService.create(view);
        return viewMapper.toDto(createdView);
    }

    @PutMapping("/update")
    public ViewDto updateView(@Validated(OnUpdate.class) @RequestBody ViewDto viewDto) {
        View view = viewMapper.toEntity(viewDto);
        View updatedView = viewService.update(view);
        return viewMapper.toDto(updatedView);
    }

    @DeleteMapping("/delete")
    public void deleteView(@RequestBody ViewDto viewDto) {
        viewService.delete(viewDto.getContentId(), viewDto.getProfileId());
    }

}
