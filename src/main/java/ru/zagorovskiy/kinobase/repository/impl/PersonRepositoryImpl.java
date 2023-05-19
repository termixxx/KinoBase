package ru.zagorovskiy.kinobase.repository.impl;

import org.springframework.stereotype.Repository;
import ru.zagorovskiy.kinobase.domain.entiti.Person;
import ru.zagorovskiy.kinobase.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepositoryImpl implements PersonRepository {
    @Override
    public Optional<Person> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Optional<Person>> findAll() {
        return null;
    }

    @Override
    public Optional<Person> findByFirstNameAndLastName(String firstName, String LastName) {
        return Optional.empty();
    }

    @Override
    public Optional<Person> findByFirstName(String firstName) {
        return Optional.empty();
    }

    @Override
    public Optional<Person> findByLastName(String LastName) {
        return Optional.empty();
    }

    @Override
    public void update(Person person) {

    }

    @Override
    public void create(Person person) {

    }

    @Override
    public void delete(Long id) {

    }
}
