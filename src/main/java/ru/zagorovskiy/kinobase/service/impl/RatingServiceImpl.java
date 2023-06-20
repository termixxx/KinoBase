package ru.zagorovskiy.kinobase.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zagorovskiy.kinobase.domain.entiti.Rating;
import ru.zagorovskiy.kinobase.domain.exception.ResourceNotFoundException;
import ru.zagorovskiy.kinobase.repository.RatingRepository;
import ru.zagorovskiy.kinobase.service.RatingService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Rating> getAllByContentId(Long contentId) {
        log.info("Getting all ratings by content ID: " + contentId);
        return ratingRepository.findAllByContentId(contentId)
                .orElseThrow(() -> {
                    log.error("Ratings not found for content ID: " + contentId);
                    return new ResourceNotFoundException("Ratings not found");
                });
    }

    @Override
    @Transactional
    public Rating update(Rating rating) {
        log.info("Updating rating with contentID: " + rating.getContentId() + " and profileID: " + rating.getProfileId());
        ratingRepository.update(rating);
        return rating;
    }

    @Override
    @Transactional
    public Rating create(Rating rating) {
        log.info("Creating rating");
        if (ratingRepository.findByContentIdAndProfileId(rating.getContentId(), rating.getProfileId()).isPresent()) {
            throw new IllegalStateException("Rating already exists.");
        }
        ratingRepository.create(rating);
        return rating;
    }

    @Override
    @Transactional
    public void delete(Long contentId, Long profileId) {
        log.info("Deleting rating for content ID: " + contentId + " and profile ID: " + profileId);
        ratingRepository.delete(contentId, profileId);
    }

    @Override
    @Transactional
    public void deleteAllByProfileId(Long profileId) {
        log.info("Deleting all ratings by profile ID: " + profileId);
        ratingRepository.deleteAllByProfileId(profileId);
    }

    @Override
    @Transactional
    public void deleteAllByContentId(Long contentId) {
        log.info("Deleting all ratings by content ID: " + contentId);
        ratingRepository.deleteAllByContentId(contentId);
    }
}
