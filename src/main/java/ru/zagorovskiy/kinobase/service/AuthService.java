package ru.zagorovskiy.kinobase.service;

import org.springframework.stereotype.Service;
import ru.zagorovskiy.kinobase.web.dto.auth.JwtRequest;
import ru.zagorovskiy.kinobase.web.dto.auth.JwtResponse;

@Service
public interface AuthService {
    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);
}
