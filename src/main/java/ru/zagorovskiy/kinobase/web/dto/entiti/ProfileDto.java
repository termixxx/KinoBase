package ru.zagorovskiy.kinobase.web.dto.entiti;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.zagorovskiy.kinobase.domain.enums.Role;
import ru.zagorovskiy.kinobase.web.dto.validation.OnCreate;
import ru.zagorovskiy.kinobase.web.dto.validation.OnUpdate;

import java.util.Set;

@Data
@Schema(description = "Profile DTO")
public class ProfileDto {

    private Long id;

    @Schema(example = "anatoly")
    @NotNull(message = "login cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private String login;

    @Schema(example = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "password cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private String password;

    @Schema(example = "zagorovski20@gmail.com")
    @Email
    @NotNull(message = "email cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private String email;

    @Schema(example = "termix")
    private String nickname;

    @Schema(example = "[\"ROLE_ADMIN\"]")
    private Set<Role> roles;
}
