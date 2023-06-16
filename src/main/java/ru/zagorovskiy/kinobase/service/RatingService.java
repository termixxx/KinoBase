package ru.zagorovskiy.kinobase.service;

import org.springframework.stereotype.Service;
import ru.zagorovskiy.kinobase.domain.entiti.Rating;

import java.util.List;

@Service
public interface RatingService {
    List<Rating> getAllByContentId(Long contentId);

    Rating update(Rating rating);

    Rating create(Rating rating);

    void delete(Long contentId, Long profileId);

    void deleteAllByProfileId(Long profileId);

    void deleteAllByContentId(Long contentId);
}
