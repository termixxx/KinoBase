package ru.zagorovskiy.kinobase.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.zagorovskiy.kinobase.domain.entiti.Person;
import ru.zagorovskiy.kinobase.repository.PersonRepository;
import ru.zagorovskiy.kinobase.repository.mappers.PersonRowMapper;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Person> findById(Long id) {
        String query = "SELECT id, first_name, last_name, position FROM person " +
                "WHERE id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(query, new PersonRowMapper(), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Person>> findAll() {
        String query = "SELECT id, first_name, last_name, position FROM person";
        try {
            return Optional.of(jdbcTemplate.query(query, new PersonRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Person> findByFirstNameAndLastName(String firstName, String lastName) {
        String query = "SELECT id, first_name, last_name, position FROM person " +
                "WHERE first_name = ? AND last_name = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(query, new PersonRowMapper(), firstName, lastName));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Person> findByFirstName(String firstName) {
        String query = "SELECT id, first_name, last_name, position FROM person " +
                "WHERE first_name = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(query, new PersonRowMapper(), firstName));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Person> findByLastName(String LastName) {
        String query = "SELECT id, first_name, last_name, position FROM person " +
                "WHERE last_name = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(query, new PersonRowMapper(), LastName));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Person person) {
        String query = "UPDATE person SET first_name = ?, last_name = ?, position = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(query, person.getFirstName(), person.getLastName(),
                person.getPosition().toString(), person.getId());
    }

    @Override
    public void create(Person person) {
        String query = "INSERT INTO person (first_name, last_name, position) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, person.getFirstName(), person.getLastName(), person.getPosition().toString());
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM person WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}
