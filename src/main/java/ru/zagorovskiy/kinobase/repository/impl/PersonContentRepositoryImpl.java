package ru.zagorovskiy.kinobase.repository.impl;

import org.springframework.stereotype.Repository;
import ru.zagorovskiy.kinobase.domain.entiti.PersonContent;
import ru.zagorovskiy.kinobase.repository.PersonContentRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonContentRepositoryImpl implements PersonContentRepository {
    @Override
    public List<Optional<PersonContent>> findAllByContentId(Long contentId) {
        return null;
    }

    @Override
    public void create(PersonContent personContent) {

    }

    @Override
    public void delete(Long contentId, Long personId) {

    }
}
