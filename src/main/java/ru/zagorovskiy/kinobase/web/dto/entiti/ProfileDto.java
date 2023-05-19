package ru.zagorovskiy.kinobase.web.dto.entiti;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.zagorovskiy.kinobase.web.dto.validation.OnCreate;
import ru.zagorovskiy.kinobase.web.dto.validation.OnUpdate;

@Data
public class ProfileDto {
    private Long id;

    @NotNull(message = "login cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private String login;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "password cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private String password;

    @Email
    @NotNull(message = "email cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private String email;

    private String nickname;
}
