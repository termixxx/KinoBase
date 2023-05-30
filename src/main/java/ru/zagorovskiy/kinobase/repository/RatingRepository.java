package ru.zagorovskiy.kinobase.repository;

import org.springframework.stereotype.Repository;
import ru.zagorovskiy.kinobase.domain.entiti.Rating;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository {

    Optional<List<Rating>> findAllByContentId(Long contentId);

    Optional<Rating> findByContentIdAndProfileId(Long contentId, Long profileId);

    void update(Rating rating);

    void create(Rating rating);

    void delete(Long contentId, Long profileId);

}
