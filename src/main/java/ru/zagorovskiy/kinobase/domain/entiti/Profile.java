package ru.zagorovskiy.kinobase.domain.entiti;

import lombok.Data;
import ru.zagorovskiy.kinobase.domain.enums.Role;

import java.util.Set;

@Data
public class Profile {
    private Long id;
    private String login;
    private String password;
    private String email;
    private String nickname;
    private Set<Role> roles;
}
