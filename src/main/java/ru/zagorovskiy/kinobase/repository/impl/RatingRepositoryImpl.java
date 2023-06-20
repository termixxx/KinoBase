package ru.zagorovskiy.kinobase.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class RatingRepositoryImpl implements RatingRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<List<Rating>> findAllByContentId(Long contentId) {
        log.info(getClass().getName() + " findAllByContentId пошёл в бд" );
        String query = "SELECT content_id, profile_id, value FROM rating " +
                "WHERE content_id = ?";
        try {
            return Optional.of(jdbcTemplate.query(query, new RatingRowMapper(), contentId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Rating> findByContentIdAndProfileId(Long contentId, Long profileId) {
        log.info(getClass().getName() + " findByContentIdAndProfileId пошёл в бд" );
        String query = "SELECT content_id, profile_id, value FROM rating " +
                "WHERE content_id = ? AND profile_id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(query, new RatingRowMapper(), contentId, profileId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Rating rating) {
        log.info(getClass().getName() + " update пошёл в бд" );
        String query = "UPDATE rating SET value = ? " +
                "WHERE content_id = ? AND profile_id = ?";
        jdbcTemplate.update(query,
                rating.getValue(), rating.getContentId(), rating.getProfileId());
    }

    @Override
    public void create(Rating rating) {
        log.info(getClass().getName() + " create пошёл в бд" );
        String query = "INSERT INTO rating(content_id, profile_id, value) VALUES (?, ?, ?)";
        jdbcTemplate.update(query,
                rating.getContentId(), rating.getProfileId(), rating.getValue());
    }

    @Override
    public void delete(Long contentId, Long profileId) {
        log.info(getClass().getName() + " delete пошёл в бд" );
        String query = "DELETE FROM rating WHERE content_id = ? AND profile_id = ?";
        jdbcTemplate.update(query, contentId, profileId);
    }

    @Override
    public void deleteAllByProfileId(Long profileId) {
        log.info(getClass().getName() + " deleteAllByProfileId пошёл в бд" );
        String query = "DELETE FROM rating WHERE profile_id = ?";
        jdbcTemplate.update(query, profileId);
    }

    @Override
    public void deleteAllByContentId(Long contentId) {
        log.info(getClass().getName() + " deleteAllByContentId пошёл в бд" );
        String query = "DELETE FROM rating WHERE content_id = ?";
        jdbcTemplate.update(query, contentId);
    }
}
