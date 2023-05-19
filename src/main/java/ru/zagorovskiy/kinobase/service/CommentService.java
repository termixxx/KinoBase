package ru.zagorovskiy.kinobase.service;

import org.springframework.stereotype.Service;
import ru.zagorovskiy.kinobase.domain.entiti.Comment;

import java.util.List;

@Service
public interface CommentService {
    List<Comment> getAllByContentId(Long contentId);

    Comment update(Comment comment);

    Comment create(Comment comment);

    void delete(Long contentId, Long profileId);
}
