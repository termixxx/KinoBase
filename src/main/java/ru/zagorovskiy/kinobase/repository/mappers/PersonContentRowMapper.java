package ru.zagorovskiy.kinobase.repository.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.zagorovskiy.kinobase.domain.entiti.PersonContent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonContentRowMapper implements RowMapper<PersonContent> {
    @Override
    public PersonContent mapRow(ResultSet rs, int rowNum) throws SQLException {
        PersonContent personContent = new PersonContent();

        personContent.setContentId(rs.getLong("content_id"));
        personContent.setPersonId(rs.getLong("person_id"));

        return personContent;
    }
}
