package ru.zagorovskiy.kinobase.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zagorovskiy.kinobase.domain.entiti.Profile;
import ru.zagorovskiy.kinobase.service.AuthService;
import ru.zagorovskiy.kinobase.service.ProfileService;
import ru.zagorovskiy.kinobase.web.dto.auth.JwtRequest;
import ru.zagorovskiy.kinobase.web.dto.auth.JwtResponse;
import ru.zagorovskiy.kinobase.web.dto.entiti.ProfileDto;
import ru.zagorovskiy.kinobase.web.dto.mappers.ProfileMapper;
import ru.zagorovskiy.kinobase.web.dto.validation.OnCreate;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@Validated
@Tag(name = "Auth controller", description = "Auth API")
public class AuthController {
    private final AuthService authService;
    private final ProfileService profileService;
    private final ProfileMapper profileMapper;

    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody JwtRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public ProfileDto register(@Validated(OnCreate.class) @RequestBody ProfileDto profileDto) {
        Profile profile = profileMapper.toEntity(profileDto);
        Profile createdProfile = profileService.create(profile);
        return profileMapper.toDto(createdProfile);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken) {
        return authService.refresh(refreshToken);
    }
}
