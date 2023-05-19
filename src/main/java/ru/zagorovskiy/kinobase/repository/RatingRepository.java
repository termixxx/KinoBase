package ru.zagorovskiy.kinobase.repository;

import org.springframework.stereotype.Repository;
import ru.zagorovskiy.kinobase.domain.entiti.Rating;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository {

    List<Optional<Rating>> findAllByContentId(Long contentId);

    void update(Rating comment);

    void create(Rating comment);

    void delete(Long contentId, Long profileId);

}
