package ru.zagorovskiy.kinobase.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.zagorovskiy.kinobase.domain.entiti.Profile;
import ru.zagorovskiy.kinobase.service.AuthService;
import ru.zagorovskiy.kinobase.service.ProfileService;
import ru.zagorovskiy.kinobase.web.dto.auth.JwtRequest;
import ru.zagorovskiy.kinobase.web.dto.auth.JwtResponse;
import ru.zagorovskiy.kinobase.web.security.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final ProfileService profileService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        JwtResponse jwtResponse = new JwtResponse();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLogin(),
                        loginRequest.getPassword()
                )
        );
        Profile profile = profileService.getByLogin(loginRequest.getLogin());
        jwtResponse.setId(profile.getId());
        jwtResponse.setLogin(profile.getLogin());
        jwtResponse.setAccessToken(jwtTokenProvider.createAccessToken(
                profile.getId(),
                profile.getLogin(),
                profile.getRoles()
        ));
        jwtResponse.setRefreshToken(jwtTokenProvider.createRefreshToken(
                profile.getId(),
                profile.getLogin()
        ));
        return jwtResponse;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return jwtTokenProvider.refreshUserTokens(refreshToken);
    }
}
