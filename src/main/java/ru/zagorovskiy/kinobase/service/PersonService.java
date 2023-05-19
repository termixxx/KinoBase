package ru.zagorovskiy.kinobase.service;

import org.springframework.stereotype.Service;
import ru.zagorovskiy.kinobase.domain.entiti.Person;

import java.util.List;

@Service
public interface PersonService {
    Person getById(Long id);

    List<Person> getAll();

    Person update(Person person);

    Person create(Person person);

    void delete(Long id);
}
