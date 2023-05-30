package ru.zagorovskiy.kinobase.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.zagorovskiy.kinobase.domain.entiti.Content;
import ru.zagorovskiy.kinobase.domain.enums.Country;
import ru.zagorovskiy.kinobase.domain.enums.Genre;
import ru.zagorovskiy.kinobase.repository.ContentRepository;
import ru.zagorovskiy.kinobase.repository.mappers.CommentRowMapper;
import ru.zagorovskiy.kinobase.repository.mappers.ContentRowMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ContentRepositoryImpl implements ContentRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Content> findById(Long id) {
        String query = "SELECT id, genres, countries, title, description, type, release_year, poster_url " +
                "FROM content WHERE id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(query, new ContentRowMapper(), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Content> findByTitle(String title) {
        String query = "SELECT id, genres, countries, title, description, type, release_year, poster_url " +
                "FROM content WHERE title ILIKE ?";
        String searchTerm = "%" + title + "%";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(query, new ContentRowMapper(), searchTerm));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Content>> findAll() {
        String query = "SELECT id, genres, countries, title, description, type, release_year, poster_url " +
                "FROM content";
        try {
            return Optional.of(jdbcTemplate.query(query, new ContentRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Content>> findAllByGenres(Genre[] genre) {
        String query = "SELECT id, genres, countries, title, description, type, release_year, poster_url " +
                "FROM content WHERE genres @> ANY(?)"; // TODO проверить работу поиска
        String[] genresList = Arrays.stream(genre)
                .map(Enum::toString)
                .toArray(String[]::new);
        try {
            return Optional.of(jdbcTemplate.query(query, new ContentRowMapper(), (Object) genresList));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Content content) {
        String query = "UPDATE content SET genres = ?, countries = ?, title = ?, description = ?, type = ?, poster_url = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(query,
                content.getGenreList().stream()
                        .map(Genre::name)
                        .toArray(String[]::new),
                content.getCountryList().stream()
                        .map(Country::name)
                        .toArray(String[]::new),
                content.getTitle(),
                content.getDescription(),
                content.getType(),
                content.getPosterUrl(),
                content.getId());
    }

    @Override
    public void create(Content content) {
        String query = "INSERT INTO content (genres, countries, title, description, type, release_year, poster_url) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query,
                content.getGenreList().stream()
                        .map(Genre::name)
                        .toArray(String[]::new),
                content.getCountryList().stream()
                        .map(Country::name)
                        .toArray(String[]::new),
                content.getTitle(),
                content.getDescription(),
                content.getType(),
                content.getReleaseYear(),
                content.getReleaseYear(),
                content.getPosterUrl());
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM content WHERE id = ?";
        jdbcTemplate.update(query,id);
    }
}
