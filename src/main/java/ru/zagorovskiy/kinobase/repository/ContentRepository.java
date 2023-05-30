package ru.zagorovskiy.kinobase.repository;

import org.springframework.stereotype.Repository;
import ru.zagorovskiy.kinobase.domain.entiti.Content;
import ru.zagorovskiy.kinobase.domain.enums.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepository {

    Optional<Content> findById(Long id);

    Optional<Content> findByTitle(String title);

    Optional<List<Content>> findAll();

    Optional<List<Content>> findAllByGenres(Genre[] genre);

    void update(Content content);

    void create(Content content);

    void delete(Long id);
}
