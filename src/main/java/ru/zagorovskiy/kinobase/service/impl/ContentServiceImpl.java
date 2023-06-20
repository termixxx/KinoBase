package ru.zagorovskiy.kinobase.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zagorovskiy.kinobase.domain.entiti.Content;
import ru.zagorovskiy.kinobase.domain.enums.Genre;
import ru.zagorovskiy.kinobase.domain.exception.ResourceNotFoundException;
import ru.zagorovskiy.kinobase.repository.ContentRepository;
import ru.zagorovskiy.kinobase.service.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContentServiceImpl implements ContentService {
    private final ContentRepository contentRepository;

    private final ViewService viewService;
    private final CommentService commentService;
    private final RatingService ratingService;
    private final PersonContentService personContentService;

    @Override
    @Transactional(readOnly = true)
    public Content getById(Long id) {
        log.info("Getting content by ID: " + id);
        return contentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Content not found for ID: " + id);
                    return new ResourceNotFoundException("Content not found");
                });
    }

    @Override
    @Transactional(readOnly = true)
    public Content getByTitle(String title) {
        log.info("Getting content by title: " + title);
        return contentRepository.findByTitle(title)
                .orElseThrow(() -> {
                    log.error("Content not found for title: " + title);
                    return new ResourceNotFoundException("Content not found");
                });
    }

    @Override
    @Transactional(readOnly = true)
    public List<Content> getAll() {
        log.info("Getting all content");
        return contentRepository.findAll()
                .orElseThrow(() -> {
                    log.error("Content not found");
                    return new ResourceNotFoundException("Content not found");
                });
    }

    @Override
    @Transactional(readOnly = true)
    public List<Content> getAllByGenres(Genre[] genres) {
        log.info("Getting all content by genres");
        return contentRepository.findAllByGenres(genres)
                .orElseThrow(() -> {
                    log.error("Content not found for genres: " + Arrays.toString(genres));
                    return new ResourceNotFoundException("Content not found");
                });
    }

    @Override
    @Transactional
    public Content update(Content content) {
        log.info("Updating content with ID: " + content.getId());
        contentRepository.update(content);
        return content;
    }

    @Override
    @Transactional
    public Content create(Content content) {
        log.info("Creating content");
        Optional<Content> foundedContent = contentRepository.findByTitle(content.getTitle());
        if (foundedContent.isPresent()) {
            if (foundedContent.get().getReleaseDate() == content.getReleaseDate()) {
                throw new IllegalStateException("Content already exists.");
            }
        }
        contentRepository.create(content);
        return content;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting content with ID: " + id);
        viewService.deleteAllByContentId(id);
        commentService.deleteAllByContentId(id);
        ratingService.deleteAllByContentId(id);
        personContentService.deleteAllByContentId(id);

        contentRepository.delete(id);
    }
}
