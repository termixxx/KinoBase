package ru.zagorovskiy.kinobase.web.dto.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JwtRequest {
    @NotNull(message = "login cannot be empty")
    private String login;
    @NotNull(message = "password cannot be empty")
    private String password;
}
