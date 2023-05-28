package ru.zagorovskiy.kinobase.repository.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.zagorovskiy.kinobase.domain.entiti.Profile;
import ru.zagorovskiy.kinobase.domain.enums.Role;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ProfileRowMapper implements RowMapper<Profile> {

    @Override
    public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
        Profile profile = new Profile();

        profile.setId(rs.getLong("id"));
        profile.setLogin(rs.getString("login"));
        profile.setPassword(rs.getString("password"));
        profile.setEmail(rs.getString("email"));
        profile.setNickname(rs.getString("nickname"));
        profile.setRoles(getRoles(rs.getArray("role")));

        return profile;
    }

    private Set<Role> getRoles(Array array) throws SQLException {
        String[] roles = (String[]) array.getArray();
        return Arrays.stream(roles)
                .map(Role::valueOf)
                .collect(Collectors.toSet());
    }
}
