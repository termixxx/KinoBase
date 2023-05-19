package ru.zagorovskiy.kinobase.repository;

import org.springframework.stereotype.Repository;
import ru.zagorovskiy.kinobase.domain.entiti.PersonContent;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonContentRepository {

    List<Optional<PersonContent>> findAllByContentId(Long contentId);

    // List<Optional<PersonContent>> findAllByPersonId(Long personId);

    void create(PersonContent personContent);

    void delete(Long contentId, Long personId);
}
