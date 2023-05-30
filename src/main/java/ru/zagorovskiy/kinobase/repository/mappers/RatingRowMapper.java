package ru.zagorovskiy.kinobase.repository.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.zagorovskiy.kinobase.domain.entiti.Rating;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingRowMapper implements RowMapper<Rating> {
    @Override
    public Rating mapRow(ResultSet rs, int rowNum) throws SQLException {
        Rating rating = new Rating();

        rating.setContentId(rs.getLong("content_id"));
        rating.setProfileId(rs.getLong("profile_id"));
        rating.setValue(rs.getInt("value"));

        return rating;
    }
}
