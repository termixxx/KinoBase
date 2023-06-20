package ru.zagorovskiy.kinobase.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CommentRepositoryImpl implements CommentRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<List<Comment>> findAllByContentId(Long contentId) {
        log.info(getClass().getName() + " findAllByContentId пошёл в бд" );
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
        log.info(getClass().getName() + " findByMessage пошёл в бд" );
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
        log.info(getClass().getName() + " update пошёл в бд" );
        String query = "UPDATE comment SET message = ? WHERE id = ?";
        jdbcTemplate.update(query, comment.getComment(), comment.getId());
    }

    @Override
    public void create(Comment comment) {
        log.info(getClass().getName() + " create пошёл в бд" );
        String query = "INSERT INTO comment (content_id, profile_id, message, added_at) " +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, comment.getContentId(), comment.getProfileId(),
                comment.getComment(), comment.getAddedAt());
    }

    @Override
    public void delete(Long id) {
        log.info(getClass().getName() + " delete пошёл в бд" );
        String query = "DELETE FROM comment WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public void deleteAllByProfileId(Long profileId) {
        log.info(getClass().getName() + " deleteAllByProfileId пошёл в бд" );
        String query = "DELETE FROM comment WHERE profile_id = ?";
        jdbcTemplate.update(query, profileId);
    }

    @Override
    public void deleteAllByContentId(Long contentId) {
        log.info(getClass().getName() + " deleteAllByContentId пошёл в бд" );
        String query = "DELETE FROM comment WHERE content_id = ?";
        jdbcTemplate.update(query, contentId);
    }
}
