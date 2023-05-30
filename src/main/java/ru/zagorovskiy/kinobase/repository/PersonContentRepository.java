package ru.zagorovskiy.kinobase.repository;

import org.springframework.stereotype.Repository;
import ru.zagorovskiy.kinobase.domain.entiti.PersonContent;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonContentRepository {

    Optional<List<PersonContent>> findAllByContentId(Long contentId);

    Optional<List<PersonContent>> findAllByPersonId(Long personId);

    Optional<PersonContent> findByContentIdAndPersonId(Long contentId, Long personId);

    void create(PersonContent personContent);

    void delete(Long contentId, Long personId);
}
