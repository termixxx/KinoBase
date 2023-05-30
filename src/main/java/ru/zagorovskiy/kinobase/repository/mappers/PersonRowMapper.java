package ru.zagorovskiy.kinobase.repository.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.zagorovskiy.kinobase.domain.entiti.Person;
import ru.zagorovskiy.kinobase.domain.enums.Position;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();

        person.setId(rs.getLong("id"));
        person.setFirstName(rs.getString("first_name"));
        person.setLastName(rs.getString("last_name"));
        person.setPosition(Position.valueOf(rs.getString("position")));

        return person;
    }
}
