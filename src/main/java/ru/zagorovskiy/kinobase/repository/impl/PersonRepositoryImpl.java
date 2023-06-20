package ru.zagorovskiy.kinobase.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class PersonRepositoryImpl implements PersonRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Person> findById(Long id) {
        log.info(getClass().getName() + " findById пошёл в бд" );
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
        log.info(getClass().getName() + " findAll пошёл в бд" );
        String query = "SELECT id, first_name, last_name, position FROM person";
        try {
            return Optional.of(jdbcTemplate.query(query, new PersonRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Person> findByFirstNameAndLastName(String firstName, String lastName) {
        log.info(getClass().getName() + " findByFirstNameAndLastName пошёл в бд" );
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
        log.info(getClass().getName() + " findByFirstName пошёл в бд" );
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
        log.info(getClass().getName() + " findByLastName пошёл в бд" );
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
        log.info(getClass().getName() + " update пошёл в бд" );
        String query = "UPDATE person SET first_name = ?, last_name = ?, position = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(query, person.getFirstName(), person.getLastName(),
                person.getPosition().toString(), person.getId());
    }

    @Override
    public void create(Person person) {
        log.info(getClass().getName() + " create пошёл в бд" );
        String query = "INSERT INTO person (first_name, last_name, position) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, person.getFirstName(), person.getLastName(), person.getPosition().toString());
    }

    @Override
    public void delete(Long id) {
        log.info(getClass().getName() + " delete пошёл в бд" );
        String query = "DELETE FROM person WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}
