package ru.zagorovskiy.kinobase.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.zagorovskiy.kinobase.domain.entiti.View;
import ru.zagorovskiy.kinobase.repository.ViewRepository;
import ru.zagorovskiy.kinobase.repository.mappers.ViewRowMapper;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ViewRepositoryImpl implements ViewRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<List<View>> findAllByProfileId(Long profileId) {
        String query = "SELECT content_id, profile_id, favorite, condition, added_at FROM view " +
                "WHERE profile_id = ?";
        try {
            return Optional.of(jdbcTemplate.query(query, new ViewRowMapper(), profileId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(View view) {
        String query = "UPDATE view SET favorite = ?, condition = ? " +
                "WHERE content_id = ? AND profile_id = ?";
        jdbcTemplate.update(query, view.isFavorite(), view.getCondition().toString(),
                view.getContentId(), view.getProfileId());
    }

    @Override
    public void create(View view) {
        String query = "INSERT INTO view (content_id, profile_id, favorite, condition, added_at) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, view.getContentId(), view.getProfileId(),
                view.isFavorite(), view.getCondition().toString(), view.getAddedAt());
    }

    @Override
    public void delete(Long contentId, Long profileId) {
        String query = "DELETE FROM view " +
                "WHERE content_id = ? AND profile_id = ?";
        jdbcTemplate.update(query, contentId, profileId);
    }
}
