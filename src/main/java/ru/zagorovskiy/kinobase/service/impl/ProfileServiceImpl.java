package ru.zagorovskiy.kinobase.service.impl;

import org.springframework.stereotype.Service;
import ru.zagorovskiy.kinobase.domain.entiti.Profile;
import ru.zagorovskiy.kinobase.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Override
    public Profile getById(Long id) {
        return null;
    }

    @Override
    public Profile getByLogin(String login) {
        return null;
    }

    @Override
    public Profile update(Profile profile) {
        return null;
    }

    @Override
    public Profile create(Profile profile) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
