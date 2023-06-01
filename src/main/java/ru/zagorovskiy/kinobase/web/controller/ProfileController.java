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

    @PutMapping("/update")
    public ProfileDto updateProfile(@Validated(OnUpdate.class) @RequestBody ProfileDto dto) {
        Profile profile = profileMapper.toEntity(dto);
        Profile updatedView = profileService.update(profile);
        return profileMapper.toDto(updatedView);
    }

    @GetMapping("/{id}/views")
    public List<ViewDto> getAllByProfileId(@PathVariable Long id) {
        List<View> views = viewService.getAllByProfileId(id);
        return viewMapper.toDto(views);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        profileService.delete(id);
    }

}
