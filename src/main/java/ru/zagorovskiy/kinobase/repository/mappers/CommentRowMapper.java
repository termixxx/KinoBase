package ru.zagorovskiy.kinobase.repository.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.zagorovskiy.kinobase.domain.entiti.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CommentRowMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();

        comment.setId(rs.getLong("id"));
        comment.setContentId(rs.getLong("content_id"));
        comment.setProfileId(rs.getLong("profile_id"));
        comment.setComment(rs.getString("message"));
        comment.setAddedAt(LocalDate.parse(rs.getString("added_at")));

        return comment;
    }
}
