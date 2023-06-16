package ru.zagorovskiy.kinobase.service;

import org.springframework.stereotype.Service;
import ru.zagorovskiy.kinobase.domain.entiti.Comment;

import java.util.List;

@Service
public interface CommentService {
    List<Comment> getAllByContentId(Long contentId);

    Comment getByMessage(String message);

    Comment update(Comment comment);

    Comment create(Comment comment);

    void delete(Long id);

    void deleteAllByProfileId(Long profileId);

    void deleteAllByContentId(Long contentId);
}
