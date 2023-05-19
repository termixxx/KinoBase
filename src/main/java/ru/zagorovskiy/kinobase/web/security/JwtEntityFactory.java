package ru.zagorovskiy.kinobase.web.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.zagorovskiy.kinobase.domain.entiti.Profile;
import ru.zagorovskiy.kinobase.domain.enums.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtEntityFactory {
    public static JwtEntity create(Profile profile) {
        return new JwtEntity(
                profile.getId(),
                profile.getLogin(),
                profile.getPassword(),
                profile.getEmail(),
                profile.getNickname(),
                mapToGrantedAuthorities(new ArrayList<>(profile.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
        return roles.stream()
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
