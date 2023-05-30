package ru.zagorovskiy.kinobase.repository.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.zagorovskiy.kinobase.domain.entiti.View;
import ru.zagorovskiy.kinobase.domain.enums.Condition;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ViewRowMapper implements RowMapper<View> {

    @Override
    public View mapRow(ResultSet rs, int rowNum) throws SQLException {
        View view = new View();

        view.setContentId(rs.getLong("content_id"));
        view.setProfileId(rs.getLong("profile_id"));
        view.setFavorite(rs.getBoolean("favorite"));
        view.setCondition(Condition.valueOf(rs.getString("condition")));
        view.setAddedAt(LocalDate.now());

        return view;
    }

}
