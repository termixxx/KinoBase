package ru.zagorovskiy.kinobase.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.zagorovskiy.kinobase.domain.entiti.Rating;
import ru.zagorovskiy.kinobase.repository.RatingRepository;
import ru.zagorovskiy.kinobase.repository.mappers.RatingRowMapper;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RatingRepositoryImpl implements RatingRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<List<Rating>> findAllByContentId(Long contentId) {
        String query = "SELECT content_id, profile_id, value FROM rating " +
                "WHERE content_id = ?";
        try {
            return Optional.of(jdbcTemplate.query(query, new RatingRowMapper(), contentId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Rating rating) {
        String query = "UPDATE rating SET value = ? " +
                "WHERE content_id = ? AND profile_id = ?";
        jdbcTemplate.update(query,
                rating.getValue(), rating.getContentId(), rating.getProfileId());
    }

    @Override
    public void create(Rating rating) {
        String query = "INSERT INTO rating(content_id, profile_id, value) VALUES (?, ?, ?)";
        jdbcTemplate.update(query,
                rating.getContentId(), rating.getProfileId(), rating.getValue());
    }

    @Override
    public void delete(Long contentId, Long profileId) {
        String query = "DELETE FROM rating WHERE content_id = ? AND profile_id = ?";
        jdbcTemplate.update(query, contentId, profileId);
    }
}
