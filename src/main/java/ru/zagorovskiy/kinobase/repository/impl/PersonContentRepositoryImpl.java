package ru.zagorovskiy.kinobase.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.zagorovskiy.kinobase.domain.entiti.PersonContent;
import ru.zagorovskiy.kinobase.repository.PersonContentRepository;
import ru.zagorovskiy.kinobase.repository.mappers.PersonContentRowMapper;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PersonContentRepositoryImpl implements PersonContentRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<List<PersonContent>> findAllByContentId(Long contentId) {
        log.info(getClass() +" findAllByContentId пошёл в бд");
        String query = "SELECT content_id, person_id " +
                "FROM person_content WHERE content_id = ?";
        try {
            return Optional.of(jdbcTemplate.query(query, new PersonContentRowMapper(), contentId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<PersonContent>> findAllByPersonId(Long personId) {
        log.info(getClass() +" findAllByContentId пошёл в бд");
        String query = "SELECT content_id, person_id " +
                "FROM person_content WHERE person_id = ?";
        try {
            return Optional.of(jdbcTemplate.query(query, new PersonContentRowMapper(), personId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<PersonContent> findByContentIdAndPersonId(Long contentId, Long personId) {
        log.info(getClass() +" findAllByContentId пошёл в бд");
        String query = "SELECT content_id, person_id " +
                "FROM person_content WHERE content_id = ? AND person_id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(query, new PersonContentRowMapper(), contentId, personId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void create(PersonContent personContent) {
        log.info(getClass() +" findAllByContentId пошёл в бд");
        String query = "INSERT INTO person_content (content_id, person_id) VALUES (?, ?)";
        jdbcTemplate.update(query, personContent.getContentId(), personContent.getPersonId());
    }

    @Override
    public void deleteAllByContentId(Long contentId) {
        log.info(getClass() +" findAllByContentId пошёл в бд");
        String query = "DELETE FROM person_content WHERE content_id = ?";
        jdbcTemplate.update(query, contentId);
    }

    @Override
    public void deleteAllByPersonId(Long personId) {
        log.info(getClass() +" findAllByContentId пошёл в бд");
        String query = "DELETE FROM person_content WHERE person_id = ?";
        jdbcTemplate.update(query, personId);
    }
}
