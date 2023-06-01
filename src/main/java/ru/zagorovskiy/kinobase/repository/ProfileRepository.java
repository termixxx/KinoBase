package ru.zagorovskiy.kinobase.repository;

import org.springframework.stereotype.Repository;
import ru.zagorovskiy.kinobase.domain.entiti.Profile;

import java.util.Optional;

@Repository
public interface ProfileRepository {
    Optional<Profile> findById(Long id);

    Optional<Profile> findByLogin(String login);

    void update(Profile profile);

    void create(Profile profile);

    void delete(Long id);
}
