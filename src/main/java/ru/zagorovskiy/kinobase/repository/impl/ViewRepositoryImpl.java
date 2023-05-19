package ru.zagorovskiy.kinobase.repository.impl;

import org.springframework.stereotype.Repository;
import ru.zagorovskiy.kinobase.domain.entiti.View;
import ru.zagorovskiy.kinobase.repository.ViewRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class ViewRepositoryImpl implements ViewRepository {
    @Override
    public Optional<List<View>> findAllByProfileId(Long profileId) {
        return Optional.empty();
    }

    @Override
    public void update(View view) {

    }

    @Override
    public void create(View view) {

    }

    @Override
    public void delete(Long profileId, Long contentId) {

    }
}
