package ru.zagorovskiy.kinobase.repository;

import org.springframework.stereotype.Repository;
import ru.zagorovskiy.kinobase.domain.entiti.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository {

    Optional<List<Comment>> findAllByContentId(Long contentId);

    Optional<Comment> findByMessage(String message);

    void update(Comment comment);

    void create(Comment comment);

    void delete(Long id);
}
