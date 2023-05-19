package ru.zagorovskiy.kinobase.repository.impl;

import org.springframework.stereotype.Repository;
import ru.zagorovskiy.kinobase.domain.entiti.Comment;
import ru.zagorovskiy.kinobase.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryImpl implements CommentRepository {
    @Override
    public List<Optional<Comment>> findAllByContentId(Long contentId) {
        return null;
    }

    @Override
    public Optional<Comment> findByMessage(String message) {
        return Optional.empty();
    }

    @Override
    public void update(Comment comment) {

    }

    @Override
    public void create(Comment comment) {

    }

    @Override
    public void delete(Long id) {

    }
}
