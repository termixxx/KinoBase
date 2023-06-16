package ru.zagorovskiy.kinobase.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zagorovskiy.kinobase.domain.entiti.Person;
import ru.zagorovskiy.kinobase.domain.exception.ResourceNotFoundException;
import ru.zagorovskiy.kinobase.repository.PersonRepository;
import ru.zagorovskiy.kinobase.service.PersonContentService;
import ru.zagorovskiy.kinobase.service.PersonService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    private final PersonContentService personContentService;

    @Override
    public Person getById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll()
                .orElseThrow(() -> new ResourceNotFoundException("Persons not found"));
    }

    @Override
    public Person findByFirstNameAndLastName(String firstName, String lastName) {
        return personRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));
    }

    @Override
    public Person findByFirstName(String firstName) {
        return personRepository.findByFirstName(firstName)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));
    }

    @Override
    public Person findByLastName(String lastName) {
        return personRepository.findByLastName(lastName)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));
    }

    @Override
    public Person update(Person person) {
        personRepository.update(person);
        return person;
    }

    @Override
    public Person create(Person person) {
        if (personRepository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName()).isPresent()) {
            throw new IllegalStateException("Person already exists.");
        }
        personRepository.create(person);
        return person;
    }

    @Override
    public void delete(Long id) {
        personContentService.deleteAllByPersonId(id);
        personRepository.delete(id);
    }
}
