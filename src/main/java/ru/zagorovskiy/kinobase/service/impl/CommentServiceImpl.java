package ru.zagorovskiy.kinobase.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zagorovskiy.kinobase.domain.entiti.Comment;
import ru.zagorovskiy.kinobase.domain.exception.ResourceNotFoundException;
import ru.zagorovskiy.kinobase.repository.CommentRepository;
import ru.zagorovskiy.kinobase.service.CommentService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllByContentId(Long contentId) {
        log.info("Getting all comments by content ID: " + contentId);
        return commentRepository.findAllByContentId(contentId)
                .orElseThrow(() -> {
                    log.error("Comments not found for content ID: " + contentId);
                    return new ResourceNotFoundException("Comments not found");
                });
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getByMessage(String message) {
        log.info("Getting comment by message: " + message);
        return commentRepository.findByMessage(message)
                .orElseThrow(() -> {
                    log.error("Comment not found for message: " + message);
                    return new ResourceNotFoundException("Comment not found");
                });
    }

    @Override
    @Transactional
    public Comment update(Comment comment) {
        log.info("Updating comment with ID: " + comment.getId());
        commentRepository.update(comment);
        return comment;
    }

    @Override
    @Transactional
    public Comment create(Comment comment) {
        log.info("Creating comment");
        comment.setAddedAt(LocalDate.now());
        commentRepository.create(comment);
        return comment;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting comment with ID: " + id);
        commentRepository.delete(id);
    }

    @Override
    @Transactional
    public void deleteAllByProfileId(Long profileId) {
        log.info("Deleting all comments by profile ID: " + profileId);
        commentRepository.deleteAllByProfileId(profileId);
    }

    @Override
    @Transactional
    public void deleteAllByContentId(Long contentId) {
        log.info("Deleting all comments by content ID: " + contentId);
        commentRepository.deleteAllByContentId(contentId);
    }
}
