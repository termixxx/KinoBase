package ru.zagorovskiy.kinobase.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.zagorovskiy.kinobase.domain.entiti.Profile;
import ru.zagorovskiy.kinobase.domain.enums.Role;
import ru.zagorovskiy.kinobase.repository.ProfileRepository;
import ru.zagorovskiy.kinobase.repository.mappers.ProfileRowMapper;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProfileRepositoryImpl implements ProfileRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Profile> findById(Long id) {
        log.info(getClass().getName() + " findById пошёл в бд" );
        String query = "SELECT id, login, password, email, nickname, role FROM profile WHERE id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(query, new ProfileRowMapper(), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Profile> findByLogin(String login) {
        log.info(getClass().getName() + " findByLogin пошёл в бд" );
        String query = "SELECT id, login, password, email, nickname, role FROM profile WHERE login = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(query, new ProfileRowMapper(), login));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Profile profile) {
        log.info(getClass().getName() + " update пошёл в бд" );
        String query = "UPDATE profile SET password = ?, nickname = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(query, profile.getPassword(), profile.getNickname(), profile.getEmail(), profile.getId());
    }

    @Override
    public void create(Profile profile) {
        log.info(getClass().getName() + " create пошёл в бд" );
        String query = "INSERT INTO profile(login, password, email, nickname, role) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query,
                profile.getLogin(),
                profile.getPassword(),
                profile.getEmail(),
                profile.getNickname(),
                profile.getRoles().stream()
                        .map(Role::name)
                        .toArray(String[]::new)
        );
    }


    @Override
    public void delete(Long id) {
        log.info(getClass().getName() + " delete пошёл в бд" );
        String query = "DELETE FROM profile WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}
