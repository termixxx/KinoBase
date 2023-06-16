package ru.zagorovskiy.kinobase.service.impl;

import lombok.RequiredArgsConstructor;
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
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllByContentId(Long contentId) {
        return commentRepository.findAllByContentId(contentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comments not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getByMessage(String message) {
        return commentRepository.findByMessage(message)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
    }

    @Override
    @Transactional
    public Comment update(Comment comment) {
        commentRepository.update(comment);
        return comment;
    }

    @Override
    @Transactional
    public Comment create(Comment comment) {
        comment.setAddedAt(LocalDate.now());
        commentRepository.create(comment);
        return comment;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        commentRepository.delete(id);
    }

    @Override
    public void deleteAllByProfileId(Long profileId) {
        commentRepository.deleteAllByProfileId(profileId);
    }

    @Override
    public void deleteAllByContentId(Long contentId) {
        commentRepository.deleteAllByContentId(contentId);
    }
}
