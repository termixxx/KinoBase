package ru.zagorovskiy.kinobase.repository;

import org.springframework.stereotype.Repository;
import ru.zagorovskiy.kinobase.domain.entiti.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository {
    Optional<Person> findById(Long id);

    List<Optional<Person>> findAll();

    Optional<Person> findByFirstNameAndLastName(String firstName, String LastName);

    Optional<Person> findByFirstName(String firstName);

    Optional<Person> findByLastName(String LastName);

    void update(Person person);

    void create(Person person);

    void delete(Long id);
}
