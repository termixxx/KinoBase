package ru.zagorovskiy.kinobase.web.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Request for login")
public class JwtRequest {

    @Schema(example = "anatoly")
    @NotNull(message = "login cannot be empty")
    private String login;

    @Schema(example = "password")
    @NotNull(message = "password cannot be empty")
    private String password;
}
