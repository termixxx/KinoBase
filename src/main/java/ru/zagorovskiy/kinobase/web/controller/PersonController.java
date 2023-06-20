package ru.zagorovskiy.kinobase.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.zagorovskiy.kinobase.domain.entiti.Person;
import ru.zagorovskiy.kinobase.service.PersonService;
import ru.zagorovskiy.kinobase.web.dto.entiti.PersonDto;
import ru.zagorovskiy.kinobase.web.dto.mappers.PersonMapper;
import ru.zagorovskiy.kinobase.web.dto.validation.OnCreate;
import ru.zagorovskiy.kinobase.web.dto.validation.OnUpdate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
@RequiredArgsConstructor
@Validated
@CacheConfig(cacheNames = {"person"})
@Tag(name = "Person controller", description = "Person API")
public class PersonController {
    private final PersonService personService;
    private final PersonMapper personMapper;

    @GetMapping("/{id}")
    @Cacheable(key = "#id")
    public PersonDto getPersonById(@PathVariable Long id) {
        Person person = personService.getById(id);
        return personMapper.toDto(person);
    }

    @GetMapping
    @Cacheable() // проверить работает ли
    public List<PersonDto> getAllPersons() {
        List<Person> persons = personService.getAll();
        return personMapper.toDto(persons);
    }

    @PostMapping("/add")
    public PersonDto addPerson(@Validated(OnCreate.class) @RequestBody PersonDto personDto) {
        Person person = personMapper.toEntity(personDto);
        Person createdPerson = personService.create(person);
        return personMapper.toDto(createdPerson);
    }

    @PutMapping("/update")
    @CachePut(key = "#personDto.id")
    public PersonDto updatePerson(@Validated(OnUpdate.class) @RequestBody PersonDto personDto) {
        Person person = personMapper.toEntity(personDto);
        Person updatedPerson = personService.update(person);
        return personMapper.toDto(updatedPerson);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id")
    public void deletePerson(@PathVariable Long id) {
        personService.delete(id);
    }
}
