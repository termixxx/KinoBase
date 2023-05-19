package ru.zagorovskiy.kinobase.repository.impl;

import org.springframework.stereotype.Repository;
import ru.zagorovskiy.kinobase.domain.entiti.Profile;
import ru.zagorovskiy.kinobase.repository.ProfileRepository;

import java.util.Optional;

@Repository
public class ProfileRepositoryImpl implements ProfileRepository {
    @Override
    public Optional<Profile> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Profile> findByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public void update(Profile profile) {

    }

    @Override
    public void create(Profile profile) {

    }

    @Override
    public void delete(Long id) {

    }
}
