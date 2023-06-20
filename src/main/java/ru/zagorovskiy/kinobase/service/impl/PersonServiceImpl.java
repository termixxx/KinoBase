package ru.zagorovskiy.kinobase.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zagorovskiy.kinobase.domain.entiti.Person;
import ru.zagorovskiy.kinobase.domain.exception.ResourceNotFoundException;
import ru.zagorovskiy.kinobase.repository.PersonRepository;
import ru.zagorovskiy.kinobase.service.PersonContentService;
import ru.zagorovskiy.kinobase.service.PersonService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private static final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final PersonRepository personRepository;
    private final PersonContentService personContentService;

    @Override
    @Transactional(readOnly = true)
    public Person getById(Long id) {
        log.info("Getting person by ID: " + id);
        return personRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Person not found for ID: " + id);
                    return new ResourceNotFoundException("Person not found");
                });
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> getAll() {
        log.info("Getting all persons");
        return personRepository.findAll()
                .orElseThrow(() -> {
                    log.error("No persons found");
                    return new ResourceNotFoundException("Persons not found");
                });
    }

    @Override
    @Transactional(readOnly = true)
    public Person findByFirstNameAndLastName(String firstName, String lastName) {
        log.info("Finding person by first name: " + firstName + " and last name: " + lastName);
        return personRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> {
                    log.error("Person not found for first name: " + firstName + " and last name: " + lastName);
                    return new ResourceNotFoundException("Person not found");
                });
    }

    @Override
    @Transactional(readOnly = true)
    public Person findByFirstName(String firstName) {
        log.info("Finding person by first name: " + firstName);
        return personRepository.findByFirstName(firstName)
                .orElseThrow(() -> {
                    log.error("Person not found for first name: " + firstName);
                    return new ResourceNotFoundException("Person not found");
                });
    }

    @Override
    @Transactional(readOnly = true)
    public Person findByLastName(String lastName) {
        log.info("Finding person by last name: " + lastName);
        return personRepository.findByLastName(lastName)
                .orElseThrow(() -> {
                    log.error("Person not found for last name: " + lastName);
                    return new ResourceNotFoundException("Person not found");
                });
    }

    @Override
    @Transactional
    public Person update(Person person) {
        log.info("Updating person with ID: " + person.getId());
        personRepository.update(person);
        return person;
    }

    @Override
    @Transactional
    public Person create(Person person) {
        log.info("Creating person");
        if (personRepository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName()).isPresent()) {
            throw new IllegalStateException("Person already exists.");
        }
        personRepository.create(person);
        return person;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting person with ID: " + id);
        personContentService.deleteAllByPersonId(id);
        personRepository.delete(id);
    }
}
