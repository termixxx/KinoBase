package ru.zagorovskiy.kinobase.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zagorovskiy.kinobase.domain.entiti.Rating;
import ru.zagorovskiy.kinobase.domain.exception.ResourceNotFoundException;
import ru.zagorovskiy.kinobase.repository.RatingRepository;
import ru.zagorovskiy.kinobase.service.RatingService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;

    @Override
    public List<Rating> getAllByContentId(Long contentId) {
        return ratingRepository.findAllByContentId(contentId)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found"));
    }

    @Override
    public Rating update(Rating rating) {
        ratingRepository.update(rating);
        return rating;
    }

    @Override
    public Rating create(Rating rating) {
        if (ratingRepository.findByContentIdAndProfileId(rating.getContentId(), rating.getProfileId()).isPresent()) {
            throw new IllegalStateException("View already exists.");
        }
        ratingRepository.create(rating);
        return rating;
    }

    @Override
    public void delete(Long contentId, Long profileId) {
        ratingRepository.delete(contentId, profileId);
    }
}
