package ru.zagorovskiy.kinobase.repository.impl;

import org.springframework.stereotype.Repository;
import ru.zagorovskiy.kinobase.domain.entiti.Content;
import ru.zagorovskiy.kinobase.domain.enums.Genre;
import ru.zagorovskiy.kinobase.repository.ContentRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class ContentRepositoryImpl implements ContentRepository {
    @Override
    public Optional<Content> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Content> findByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public List<Optional<Content>> findAll() {
        return null;
    }

    @Override
    public List<Optional<Content>> findAllByGenres(Genre[] genre) {
        return null;
    }

    @Override
    public void update(Content content) {

    }

    @Override
    public void create(Content content) {

    }

    @Override
    public void delete(Long id) {

    }
}
