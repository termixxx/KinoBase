package ru.zagorovskiy.kinobase.service;

import org.springframework.stereotype.Service;
import ru.zagorovskiy.kinobase.domain.entiti.Content;
import ru.zagorovskiy.kinobase.domain.enums.Genre;

import java.util.List;

@Service
public interface ContentService {
    Content getById(Long id);

    Content getByTitle(String title);

    List<Content> getAll();

    List<Content> getAllByGenres(Genre[] genres);

    Content update(Content content);

    Content create(Content content);

    void delete(Long id);
}
