package ru.zagorovskiy.kinobase.web.dto.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.zagorovskiy.kinobase.domain.entiti.Comment;
import ru.zagorovskiy.kinobase.web.dto.entiti.CommentDto;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface CommentMapper {
    CommentDto toDto(Comment comment);

    List<CommentDto> toDto(List<Comment> comments);

    Comment toEntity(CommentDto dto);
}
