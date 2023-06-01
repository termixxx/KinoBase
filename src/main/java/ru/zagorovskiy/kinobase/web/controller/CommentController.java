package ru.zagorovskiy.kinobase.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.zagorovskiy.kinobase.domain.entiti.Comment;
import ru.zagorovskiy.kinobase.service.CommentService;
import ru.zagorovskiy.kinobase.web.dto.entiti.CommentDto;
import ru.zagorovskiy.kinobase.web.dto.mappers.CommentMapper;
import ru.zagorovskiy.kinobase.web.dto.validation.OnCreate;
import ru.zagorovskiy.kinobase.web.dto.validation.OnUpdate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
@Validated
@Tag(name = "Comment controller", description = "Comment API")
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @GetMapping("/{contentId}")
    public List<CommentDto> getAllCommentsByContentId(@PathVariable Long contentId) {
        List<Comment> comments = commentService.getAllByContentId(contentId);
        return commentMapper.toDto(comments);
    }

    @PostMapping("/add")
    public CommentDto addComment(@Validated(OnCreate.class) @RequestBody CommentDto commentDto) {
        Comment comment = commentMapper.toEntity(commentDto);
        Comment createdComment = commentService.create(comment);
        return commentMapper.toDto(createdComment);
    }

    @PutMapping("/update")
    public CommentDto updateComment(@Validated(OnUpdate.class) @RequestBody CommentDto commentDto) {
        Comment comment = commentMapper.toEntity(commentDto);
        Comment updatedComment = commentService.update(comment);
        return commentMapper.toDto(updatedComment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.delete(id);
    }
}
