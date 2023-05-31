package ru.zagorovskiy.kinobase.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.zagorovskiy.kinobase.domain.entiti.Profile;
import ru.zagorovskiy.kinobase.domain.entiti.View;
import ru.zagorovskiy.kinobase.service.ProfileService;
import ru.zagorovskiy.kinobase.service.ViewService;
import ru.zagorovskiy.kinobase.web.dto.entiti.ProfileDto;
import ru.zagorovskiy.kinobase.web.dto.entiti.ViewDto;
import ru.zagorovskiy.kinobase.web.dto.mappers.ProfileMapper;
import ru.zagorovskiy.kinobase.web.dto.mappers.ViewMapper;
import ru.zagorovskiy.kinobase.web.dto.validation.OnCreate;
import ru.zagorovskiy.kinobase.web.dto.validation.OnUpdate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
@Validated
@Tag(name = "Profile controller", description = "Profile API")
public class ProfileController {
    private final ProfileService profileService;
    private final ProfileMapper profileMapper;

    private final ViewService viewService;
    private final ViewMapper viewMapper;

    @GetMapping("/{id}")
    public ProfileDto getById(@PathVariable Long id) {
        Profile profile = profileService.getById(id);
        return profileMapper.toDto(profile);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        profileService.delete(id);
    }

    @PutMapping("/{id}/view")
    public ViewDto update(@PathVariable Long id,
                          @Validated(OnUpdate.class) @RequestBody ViewDto dto) {

        View view = viewMapper.toEntity(dto);
        View updatedView = viewService.update(view);
        return viewMapper.toDto(updatedView);
    }

    @GetMapping("/{id}/view")
    public List<ViewDto> getAllByProfileId(@PathVariable Long id) {
        List<View> views = viewService.getAllByProfileId(id);
        return viewMapper.toDto(views);
    }

    @PostMapping("/{id}/view")
    public ViewDto createView(@PathVariable Long id,
                              @Validated(OnCreate.class) @RequestBody ViewDto dto) {
        dto.setProfileId(id);
        View view = viewMapper.toEntity(dto);
        View createdView = viewService.create(view);
        return viewMapper.toDto(createdView);
    }

}
