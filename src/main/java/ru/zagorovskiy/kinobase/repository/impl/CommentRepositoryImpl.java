package ru.zagorovskiy.kinobase.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.zagorovskiy.kinobase.domain.entiti.Comment;
import ru.zagorovskiy.kinobase.repository.CommentRepository;
import ru.zagorovskiy.kinobase.repository.mappers.CommentRowMapper;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<List<Comment>> findAllByContentId(Long contentId) {
        String query = "SELECT id, content_id, profile_id, message, added_at " +
                "FROM comment WHERE content_id = ?";
        try {
            return Optional.of(jdbcTemplate.query(query, new CommentRowMapper(), contentId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Comment> findByMessage(String message) {
        String query = "SELECT id, content_id, profile_id, message, added_at " +
                "FROM comment WHERE message ILIKE ?";
        String searchTerm = "%" + message + "%";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(query, new CommentRowMapper(), searchTerm));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Comment comment) {
        String query = "UPDATE comment SET message = ? WHERE id = ?";
        jdbcTemplate.update(query, comment.getComment(), comment.getId());
    }

    @Override
    public void create(Comment comment) {
        String query = "INSERT INTO comment (content_id, profile_id, message, added_at) " +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, comment.getContentId(), comment.getProfileId(),
                comment.getComment(), comment.getAddedAt());
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM comment WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}
