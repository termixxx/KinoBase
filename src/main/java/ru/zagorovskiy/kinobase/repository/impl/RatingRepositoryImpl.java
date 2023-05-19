package ru.zagorovskiy.kinobase.repository.impl;

import org.springframework.stereotype.Repository;
import ru.zagorovskiy.kinobase.domain.entiti.Rating;
import ru.zagorovskiy.kinobase.repository.RatingRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class RatingRepositoryImpl implements RatingRepository {
    @Override
    public List<Optional<Rating>> findAllByContentId(Long contentId) {
        return null;
    }

    @Override
    public void update(Rating comment) {

    }

    @Override
    public void create(Rating comment) {

    }

    @Override
    public void delete(Long contentId, Long profileId) {

    }
}
