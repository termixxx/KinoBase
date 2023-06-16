package ru.zagorovskiy.kinobase.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zagorovskiy.kinobase.domain.entiti.Content;
import ru.zagorovskiy.kinobase.domain.enums.Genre;
import ru.zagorovskiy.kinobase.domain.exception.ResourceNotFoundException;
import ru.zagorovskiy.kinobase.repository.ContentRepository;
import ru.zagorovskiy.kinobase.service.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {
    private final ContentRepository contentRepository;

    private final ViewService viewService;
    private final CommentService commentService;
    private final RatingService ratingService;
    private final PersonContentService personContentService;

    @Override
    @Transactional(readOnly = true)
    public Content getById(Long id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Content not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public Content getByTitle(String title) {
        return contentRepository.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("Content not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Content> getAll() {
        return contentRepository.findAll()
                .orElseThrow(() -> new ResourceNotFoundException("Content not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Content> getAllByGenres(Genre[] genres) {
        return contentRepository.findAllByGenres(genres)
                .orElseThrow(() -> new ResourceNotFoundException("Content not found"));
    }

    @Override
    @Transactional
    public Content update(Content content) {
        contentRepository.update(content);
        return content;
    }

    @Override
    @Transactional
    public Content create(Content content) {
        Optional<Content> foundedContent = contentRepository.findByTitle(content.getTitle());
        if (foundedContent.isPresent()) {
            if (foundedContent.get().getReleaseDate() == content.getReleaseDate()) { // если одинаковое название и год выпуска
                throw new IllegalStateException("Content already exists.");
            }
        }
        contentRepository.create(content);
        return content;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        viewService.deleteAllByContentId(id);
        commentService.deleteAllByContentId(id);
        ratingService.deleteAllByContentId(id);
        personContentService.deleteAllByContentId(id);

        contentRepository.delete(id);
    }
}
