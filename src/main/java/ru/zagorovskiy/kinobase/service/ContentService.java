package ru.zagorovskiy.kinobase.service;

import org.springframework.stereotype.Service;
import ru.zagorovskiy.kinobase.domain.entiti.Content;

import java.util.List;

@Service
public interface ContentService {
    Content getById(Long id);

    Content getByTitle(String title);

    List<Content> getAll();

    Content update(Content content);

    Content create(Content content);

    void delete(Long id);
}
